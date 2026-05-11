package com.Market.ProductosProveedores.Dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    /**
     * identificacion del empleado
     */
    private String identificationNumber;

    /**
     * Contraseña del empleado
     */
    private String password;
}
