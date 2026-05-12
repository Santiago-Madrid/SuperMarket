package com.Market.ProductosProveedores.Service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Enums.PositionEmployee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
     /**
     * Inyectamos la clave secreta en el service que viene del yaml
     */
    @Value("${security.jwt.secret-key}")
    String secretKey;

    /**
     * Inyectamos la clave secreta en el service que viene del yaml
     */
    @Value("${security.jwt.token-expiration}")
    Long tokenExpiration;

    /**
     * Transforma la clave secreta de String (BASE64) a un obejto SecretKey
     * utilizable por la libreria
     * 
     * @return firma secreta
     */
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generar el token de seguridad al iniciar sesion
     * 
     * @param employeeId
     * @param positionEmployeeId
     * @param fullNameEmployee
     * @return jwt
     */
    public String generateToken(Long employeeId, PositionEmployee positionEmployeeId, String fullNameEmployee) {
        return Jwts.builder()
                .claim("employeeId", employeeId)
                .claim("positionEmployeeId", positionEmployeeId)
                .subject(fullNameEmployee)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration)) 
                .signWith(getSignKey())
                .compact();
    }

     /**
     * Verifica si el token es válido
     * 
     * @param token
     * @return boleano
     */
    public Boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extraer todos los claims del token
     * 
     * @param <T>
     * @param token
     * @param resolver
     * @return
     */
    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }

    /**
     * Extraer el nombre de empleado del token
     * 
     * @param token
     * @return nombre de empleado
     */
    public String extractFullName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Extrae el id del empleado
     * 
     * @param token
     * @return id del empleado
     */
    public Long extractEmployeeId(String token) {
        return extractClaims(token, claims -> claims.get("employeeId", Long.class));
    }

    /**
     * Extrae el rol del empleado
     * 
     * @param token
     * @return position del empleado
     */
    public PositionEmployee extractPositionId(String token) {
        return extractClaims(token, claims -> claims.get("positionEmployeeId", PositionEmployee.class));
    }

        /**
        * Refrescar el token de seguridad al iniciar sesion 
        * 
        * @param token
        * @return nuevo jwt
        * @throws Exception
        */

    public String refreshToken(String token) throws Exception {

    Claims claims = Jwts.parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

    PositionEmployee positionEmployee = PositionEmployee.valueOf(claims.get("positionEmployeeId", String.class)
            );

    return generateToken(
            claims.get("employeeId", Long.class),
            positionEmployee,
            claims.getSubject()
    );
}
}
