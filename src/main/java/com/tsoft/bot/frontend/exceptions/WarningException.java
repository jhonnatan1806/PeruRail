package com.tsoft.bot.frontend.exceptions;

public class WarningException extends Exception{
    public WarningException(String errorMessage){
        super(errorMessage);
    }
    public WarningException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
