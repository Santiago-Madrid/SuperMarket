package com.Market.ProductosProveedores.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Dto.HttpGlobalResponse;
import com.Market.ProductosProveedores.Dto.JwtDTO;
import com.Market.ProductosProveedores.Dto.LoginRequestDTO;
import com.Market.ProductosProveedores.Entity.EmployeeEntity;
import com.Market.ProductosProveedores.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {

    /**
     * Repositorio de empleados
     */
    private final EmployeeRepository employeeRepository;

    /**
     * Encriptación de contraseñas
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Servicio de jwt
     */
    private final JwtService jwtService;

    

    /**
     * Inicio de sesión de empleados
     * 
     * @param request
     * @return HttpGlobalResponse<JwtDTO>
     */
    public HttpGlobalResponse<JwtDTO> login(LoginRequestDTO request) {
        HttpGlobalResponse<JwtDTO> response = new HttpGlobalResponse<>();
        Optional<EmployeeEntity> employeeFound = employeeRepository.findByIdentificationNumber(request.getIdentificationNumber());

        if (employeeFound.isEmpty()) {
            response.setMessage("Este empleado no se encuentra registrado");
            return response;
        }

        EmployeeEntity employee = employeeFound.get();

        System.out.println("....-.-.-.-.-.-.-.-.request:"+request.getPassword()+"Employee"+employee.getPassword());

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            response.setMessage("Identificación o contraseña son incorrectos");
            return response;
        }

        JwtDTO jwtDTO = new JwtDTO();
        String jwt = jwtService.generateToken(employee.getId(), employee.getPosition(), employee.getFullName());
        jwtDTO.setJwt(jwt);
        response.setMessage("Inicio de sesión exitoso");
        response.setData(jwtDTO);
        return response;
    }

}

