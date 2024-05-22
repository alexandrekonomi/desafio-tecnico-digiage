package com.example.exception;

import java.util.Objects;

public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String filedName;
    private Object fieldValue;
    public ResourceNotFoundException(String resourceName, String filedName, Object fieldValue) {
        super(String.format("%s not fount with %s : '%s'", resourceName , filedName, fieldValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFiledName() {
        return filedName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
