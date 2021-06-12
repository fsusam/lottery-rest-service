package com.poppulo.hometest.lottery.controller;

import com.poppulo.hometest.lottery.exception.LockedTicketException;
import com.poppulo.hometest.lottery.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {TicketController.class})
public class ExceptionController {

    @ExceptionHandler(value = LockedTicketException.class)
    public ResponseEntity handleLockedTicketException(LockedTicketException e){
       return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoDataFoundException.class)
    public ResponseEntity handleLockedTicketException(NoDataFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
