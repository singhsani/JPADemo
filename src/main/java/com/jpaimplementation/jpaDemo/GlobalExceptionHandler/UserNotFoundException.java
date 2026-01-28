package com.jpaimplementation.jpaDemo.GlobalExceptionHandler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}
