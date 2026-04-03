package com.Market.ProductosProveedores.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Market.ProductosProveedores.Entity.ProductSupplierEntity;
import com.Market.ProductosProveedores.Entity.ProductSupplierId;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplierEntity, ProductSupplierId> {
    boolean existsByIdProductIdAndIdSupplierId(Long productId, Long supplierId);
}
