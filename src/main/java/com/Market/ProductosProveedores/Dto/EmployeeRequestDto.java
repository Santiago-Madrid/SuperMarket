package com.Market.ProductosProveedores.Dto;

import java.time.LocalDate;
import java.math.BigDecimal;

import com.Market.ProductosProveedores.Entity.PositionEmployee;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class EmployeeRequestDto {
    
    @NotNull(message = "El numero de identificación es obligatorio")
    private String identificationNumber;

    @NotNull(message = "El nombre completo es obligatorio")
    private String fullName;

    @NotNull(message = "El cargo es obligatorio")
    private PositionEmployee position;

    @NotNull(message = "La fecha de contratación es obligatoria")
    private LocalDate hireDate;

    @NotNull(message = "El salario es obligatorio")
    private BigDecimal salary;

    private boolean active = true;

}
