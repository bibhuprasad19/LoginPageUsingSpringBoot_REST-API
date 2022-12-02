package com.account.loginPage.exception;

public class NoSuchAccountExistsException extends RuntimeException{
    private String message;

    public NoSuchAccountExistsException(){

    }
    public NoSuchAccountExistsException(String msg){
        super(msg);
        this.message=msg;
    }

}
