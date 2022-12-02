package com.account.loginPage.exception;

public class DuplicateEmailIdExistsException extends RuntimeException{

    private String message;

    public DuplicateEmailIdExistsException(){}
    public DuplicateEmailIdExistsException(String msg){
        super(msg);
        this.message=msg;
    }
}
