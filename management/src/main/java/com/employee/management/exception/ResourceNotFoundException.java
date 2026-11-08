package com.employee.management.exception;
//	استثناء عند عدم وجود الموظف
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
