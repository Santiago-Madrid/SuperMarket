package com.Market.ProductosProveedores.Entity;

import java.util.Arrays;

import com.Market.ProductosProveedores.Exceptions.BadRequestException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum PositionEmployee {
    ADMINISTRATOR,
    CASHIER,
    ASSISTANT;


    @JsonCreator    
    public static PositionEmployee positionValidatPositionEmployee(String positionEmployee) {
        for (PositionEmployee position : PositionEmployee.values()) {
            if (position.name().equalsIgnoreCase(positionEmployee)) {
                return position;
            }
        }
        throw new BadRequestException("El cargo no es válido, debe ser uno de los siguientes: " + Arrays.toString(PositionEmployee.values()));
        
    }

}
