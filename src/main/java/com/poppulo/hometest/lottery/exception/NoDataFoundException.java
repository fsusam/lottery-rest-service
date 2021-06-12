package com.poppulo.hometest.lottery.exception;

public class NoDataFoundException extends RuntimeException{
    private static final long serialVersionUID = 1984563944383142328L;

    public NoDataFoundException(Throwable cause){
        super(cause);
    }

    public NoDataFoundException(String message){
        super(message);
    }

    public NoDataFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
