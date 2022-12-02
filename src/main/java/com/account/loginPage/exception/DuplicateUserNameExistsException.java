package com.account.loginPage.exception;

public class DuplicateUserNameExistsException extends RuntimeException {
    String message;
    public DuplicateUserNameExistsException(){}


    public DuplicateUserNameExistsException(String msg){
        super(msg);
        this.message=msg;
    }
}
