package com.Market.ProductosProveedores.Dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SaleDetailResponseDto {

    private Long productId;
    private String productName;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
