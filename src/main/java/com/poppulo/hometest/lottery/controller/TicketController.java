package com.poppulo.hometest.lottery.controller;

import com.poppulo.hometest.lottery.dto.TicketDto;
import com.poppulo.hometest.lottery.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createTicket(@RequestBody TicketDto ticketDto) {
        logger.info(" Ticket id : {}",ticketDto.getTicketId());
        String result = ticketService.createTicket();

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TicketDto>> getAllTicket() {
        List<TicketDto> result = ticketService.getTickets();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTicketById(@PathVariable long id) {
        String result = ticketService.getTicketById();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateTicketById(@PathVariable long id) {
        String result = ticketService.updateTicket();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
    public ResponseEntity<String> getStatusById(@PathVariable long id) {
        String result = ticketService.updateTicket();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
