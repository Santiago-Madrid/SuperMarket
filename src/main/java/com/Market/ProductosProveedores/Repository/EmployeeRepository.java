package com.Market.ProductosProveedores.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Market.ProductosProveedores.Entity.EmployeeEntity;
import com.Market.ProductosProveedores.Entity.PositionEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

        List<EmployeeEntity> findByPositionAndActiveTrue(PositionEmployee position);

    
} 
