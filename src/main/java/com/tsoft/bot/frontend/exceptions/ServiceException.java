package com.tsoft.bot.frontend.exceptions;

public class ServiceException extends Exception{

    public ServiceException(String errorMessage){
        super(errorMessage);
    }
    public ServiceException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public ServiceException(Throwable err) {
        super(err);
    }

}
