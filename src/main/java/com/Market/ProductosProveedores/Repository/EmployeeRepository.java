package com.Market.ProductosProveedores.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Market.ProductosProveedores.Entity.EmployeeEntity;
import com.Market.ProductosProveedores.Enums.PositionEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

        List<EmployeeEntity> findByPositionAndActiveTrue(PositionEmployee position);

        List<EmployeeEntity> findByHireDateBetweenAndActiveTrue(LocalDate startDate, LocalDate endDate);
    
        Optional<EmployeeEntity> findByIdentificationNumber(String identificationNumber);
        
} 
