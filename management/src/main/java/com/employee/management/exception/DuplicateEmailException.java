package com.employee.management.exception;
//	عند تكرار البريد الإلكتروني
public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message){
        super(message);
    }
}
