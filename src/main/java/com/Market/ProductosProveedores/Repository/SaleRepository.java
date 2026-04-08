package com.Market.ProductosProveedores.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Market.ProductosProveedores.Entity.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

}
