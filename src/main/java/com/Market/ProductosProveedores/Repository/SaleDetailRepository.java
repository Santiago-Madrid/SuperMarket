package com.Market.ProductosProveedores.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Market.ProductosProveedores.Entity.SaleDetailEntity;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {

}
