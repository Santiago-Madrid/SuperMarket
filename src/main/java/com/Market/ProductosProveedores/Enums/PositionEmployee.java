package com.Market.ProductosProveedores.Enums;

import java.util.Arrays;

import com.Market.ProductosProveedores.Exceptions.BadRequestException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum PositionEmployee {
    ADMINISTRATOR(1l),
    CASHIER(2l),
    ASSISTANT(3l);

    private final Long id;

    PositionEmployee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


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
