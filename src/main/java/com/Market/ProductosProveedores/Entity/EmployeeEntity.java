package com.Market.ProductosProveedores.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "employees")
@Entity
public class EmployeeEntity {

   @Column(name = "id")
   @Id
   @GeneratedValue(strategy =GenerationType.IDENTITY)
   private Long id;
   
   
    @Column(name = "identification_number", nullable = false, unique = true)
    private String identificationNumber;
   
    @Column(name = "full_name", nullable = false)
    private String fullName;
   
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private PositionEmployee position;
   
   
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;
   
    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;
   
   
    @Column(name = "active")
    private boolean active = true;  

}
