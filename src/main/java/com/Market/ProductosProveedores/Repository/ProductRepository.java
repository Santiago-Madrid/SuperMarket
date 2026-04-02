package com.Market.ProductosProveedores.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Market.ProductosProveedores.Entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    @Query("SELECT p FROM ProductEntity p WHERE p.State = true")
    List<ProductEntity> findAllActive();
}
