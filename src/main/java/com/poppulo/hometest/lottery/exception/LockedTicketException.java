package com.poppulo.hometest.lottery.exception;

public class LockedTicketException extends RuntimeException{
    private static final long serialVersionUID = 1984563944383142328L;

    public LockedTicketException(Throwable cause){
        super(cause);
    }

    public LockedTicketException(String message){
        super(message);
    }

    public LockedTicketException(String message, Throwable cause){
        super(message, cause);
    }
}
