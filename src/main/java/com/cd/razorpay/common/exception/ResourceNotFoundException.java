package com.cd.razorpay.common.exception;


import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceName;
    private final Object identifire;


    public ResourceNotFoundException(String resourceName, Object identifire) {
        super(resourceName + "not found "+identifire);
        this.resourceName = resourceName;
        this.identifire = identifire;
    }
}
