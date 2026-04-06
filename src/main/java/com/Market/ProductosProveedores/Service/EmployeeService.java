package com.Market.ProductosProveedores.Service;

import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Dto.EmployeeRequestDto;
import com.Market.ProductosProveedores.Dto.EmployeeResponseDto;
import com.Market.ProductosProveedores.Entity.EmployeeEntity;
import com.Market.ProductosProveedores.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;


@Service 
@RequiredArgsConstructor
public class EmployeeService {
   
    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        EmployeeEntity employee = new EmployeeEntity();

        employee.setIdentificationNumber(employeeRequestDto.getIdentificationNumber());
        employee.setFullName(employeeRequestDto.getFullName());
        employee.setPosition(employeeRequestDto.getPosition());
        employee.setHireDate(employeeRequestDto.getHireDate());
        employee.setSalary(employeeRequestDto.getSalary());
        employeeRepository.save(employee);
        
        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setId(employee.getId());
        response.setIdentificationNumber(employee.getIdentificationNumber());
        response.setFullName(employee.getFullName());
        response.setPosition(employee.getPosition());
        response.setHireDate(employee.getHireDate());
        response.setSalary(employee.getSalary());
        return response;
    }

}
