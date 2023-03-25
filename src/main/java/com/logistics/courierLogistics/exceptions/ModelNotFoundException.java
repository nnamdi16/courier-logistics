package com.logistics.courierLogistics.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ModelNotFoundException extends RuntimeException {

    private String className;
    private String argumentSupplied;
    private String errorCode;

    public ModelNotFoundException(String className, String argumentSupplied, String errorCode){
        super(argumentSupplied);
        this.className = className;
        this.argumentSupplied = argumentSupplied;
        this.errorCode = errorCode;
    }
}
