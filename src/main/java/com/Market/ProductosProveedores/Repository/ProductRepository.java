package com.Market.ProductosProveedores.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.Market.ProductosProveedores.Entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    @Query("SELECT p FROM ProductEntity p WHERE p.State = true")
    List<ProductEntity> findAllActive();

    @Modifying
    @Query("UPDATE ProductEntity p SET p.State = false WHERE p.category.id = :categoryId")
    void deactivateByCategoryId(@Param("categoryId") Long categoryId);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.State = true WHERE p.category.id = :categoryId")
    void activateByCategoryId(@Param("categoryId") Long categoryId);
}
