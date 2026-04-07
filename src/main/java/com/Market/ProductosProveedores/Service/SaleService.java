package com.Market.ProductosProveedores.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Market.ProductosProveedores.Dto.SaleDetailRequestDto;
import com.Market.ProductosProveedores.Dto.SaleDetailResponseDto;
import com.Market.ProductosProveedores.Dto.SaleRequestDto;
import com.Market.ProductosProveedores.Dto.SaleResponseDto;
import com.Market.ProductosProveedores.Entity.EmployeeEntity;
import com.Market.ProductosProveedores.Entity.ProductEntity;
import com.Market.ProductosProveedores.Entity.SaleDetailEntity;
import com.Market.ProductosProveedores.Entity.SaleEntity;
import com.Market.ProductosProveedores.Repository.EmployeeRepository;
import com.Market.ProductosProveedores.Repository.ProductRepository;
import com.Market.ProductosProveedores.Repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    private static final BigDecimal TAX_RATE = new BigDecimal("0.19");

    @Transactional
    public SaleResponseDto createSale(SaleRequestDto request) {
        EmployeeEntity employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + request.getEmployeeId()));

        SaleEntity sale = new SaleEntity();
        sale.setSaleDateTime(LocalDateTime.now());
        sale.setEmployee(employee);
        sale.setSubtotal(BigDecimal.ZERO);
        sale.setTax(BigDecimal.ZERO);
        sale.setTotal(BigDecimal.ZERO);

        sale = saleRepository.save(sale);

        List<SaleDetailEntity> details = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (SaleDetailRequestDto detailRequest : request.getDetails()) {

            ProductEntity product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detailRequest.getProductId()));

            if (product.getStock() < detailRequest.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName() +
                        ". Stock actual: " + product.getStock() + ", solicitado: " + detailRequest.getQuantity());
            }

            BigDecimal unitPrice = product.getSalePrice();
            BigDecimal quantity = new BigDecimal(detailRequest.getQuantity());
            BigDecimal detailSubtotal = unitPrice.multiply(quantity);

            SaleDetailEntity detail = new SaleDetailEntity();
            detail.setSale(sale);
            detail.setProduct(product);
            detail.setQuantity(detailRequest.getQuantity());
            detail.setUnitPrice(unitPrice);
            detail.setSubtotal(detailSubtotal);

            details.add(detail);
            product.setStock(product.getStock() - detailRequest.getQuantity());
            productRepository.save(product);

            subtotal = subtotal.add(detailSubtotal);
        }

        BigDecimal tax = subtotal.multiply(TAX_RATE);
        BigDecimal total = subtotal.add(tax);

        sale.setSubtotal(subtotal);
        sale.setTax(tax);
        sale.setTotal(total);
        sale.setDetails(details);

        sale = saleRepository.save(sale);

        return convertToResponseDto(sale);
    }

    private SaleResponseDto convertToResponseDto(SaleEntity sale) {
        SaleResponseDto response = new SaleResponseDto();
        response.setId(sale.getId());
        response.setSaleDateTime(sale.getSaleDateTime());
        response.setEmployeeId(sale.getEmployee().getId());
        response.setEmployeeName(sale.getEmployee().getFullName());
        response.setSubtotal(sale.getSubtotal());
        response.setTax(sale.getTax());
        response.setTotal(sale.getTotal());

        List<SaleDetailResponseDto> detailDtos = new ArrayList<>();
        for (SaleDetailEntity detail : sale.getDetails()) {
            SaleDetailResponseDto detailDto = new SaleDetailResponseDto();
            detailDto.setProductId(detail.getProduct().getIdProduct());
            detailDto.setProductName(detail.getProduct().getName());       
            detailDto.setQuantity(detail.getQuantity());
            detailDto.setUnitPrice(detail.getUnitPrice());
            detailDto.setSubtotal(detail.getSubtotal());
            detailDtos.add(detailDto);
        }
        response.setDetails(detailDtos);

        return response;
    }
}