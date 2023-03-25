package com.logistics.courierLogistics.exceptions;

public class ModelAlreadyExistException extends RuntimeException {

    private String fieldOrClassName;
    private String message;
    private String errorCode;

    public ModelAlreadyExistException(String fieldOrClassName, String message) {
        super(message);
        this.fieldOrClassName = fieldOrClassName;
        this.message = message;
    }

    public ModelAlreadyExistException(String fieldOrClassName, String message,String errorCode) {
        super(message);
        this.fieldOrClassName = fieldOrClassName;
        this.message = message;
        this.errorCode= errorCode;
    }

    public ModelAlreadyExistException(String message) {
        this.message = message;

    }


}
