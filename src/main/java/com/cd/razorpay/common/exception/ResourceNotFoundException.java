package com.cd.razorpay.common.exception;


import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception{

    private final String resourceName;
    private final String identifire;


    public ResourceNotFoundException(String resourceName, String identifire) {
        super(resourceName + "not found "+identifire);
        this.resourceName = resourceName;
        this.identifire = identifire;
    }
}
