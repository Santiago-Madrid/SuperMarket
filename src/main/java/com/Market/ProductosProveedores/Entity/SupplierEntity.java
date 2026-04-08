package com.Market.ProductosProveedores.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class SupplierEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSupplier;
    
    @Column(name = "nit" , nullable = false, unique = true)
    private String nit;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "phone")
    private String phone; 
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "email")
    private String email;
}
