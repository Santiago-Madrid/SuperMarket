package com.Market.ProductosProveedores.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class SaleResponseDto {

    private Long id;
    private LocalDateTime saleDateTime;
    private Long employeeId;
    private String employeeName;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;
    private List<SaleDetailResponseDto> details;
}

