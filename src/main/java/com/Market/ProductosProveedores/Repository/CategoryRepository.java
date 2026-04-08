package com.Market.ProductosProveedores.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Market.ProductosProveedores.Entity.CategoryEntity;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c JOIN FETCH c.products WHERE c.name = :name")
    Optional<CategoryEntity> findByNameWithProducts(@Param("name") String name);
}