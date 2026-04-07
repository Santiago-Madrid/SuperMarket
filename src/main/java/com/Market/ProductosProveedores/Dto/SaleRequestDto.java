package com.Market.ProductosProveedores.Dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaleRequestDto {
    @NotNull(message = "El ID del empleado es obligatorio")
    private Long employeeId;

    @NotNull(message = "Debe incluir al menos un producto")
    private List<SaleDetailRequestDto> details;
}

