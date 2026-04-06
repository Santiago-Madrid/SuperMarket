package com.Market.ProductosProveedores.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.Market.ProductosProveedores.Entity.PositionEmployee;

import lombok.Data;

@Data
public class EmployeeResponseDto {
    
    private Long id;
    private String identificationNumber;
    private String fullName;
    private PositionEmployee position;
    private LocalDate hireDate;
    private BigDecimal salary;
    private boolean active = true;

}
