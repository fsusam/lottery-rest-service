package com.poppulo.hometest.lottery.controller;

import com.poppulo.hometest.lottery.converter.RepresentationConverterApi;
import com.poppulo.hometest.lottery.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tickets", produces = "application/hal+json")
public class TicketController {
    Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    @Qualifier("ticketConverter")
    private RepresentationConverterApi representationConverterApi;

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<RepresentationModel> createTicket(@RequestParam(required = false) Integer line) {
        RepresentationModel result =representationConverterApi.toConvert(
                ticketService.createTicket(Optional.ofNullable(line).orElse(1))
        );

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Collection<RepresentationModel>> getAllTicket() {
        Collection<RepresentationModel> result =  representationConverterApi.toConvert(ticketService.getTickets());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<RepresentationModel> getTicketById(@PathVariable String id) {
        RepresentationModel result =representationConverterApi.toConvert(ticketService.getTicketById(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HttpEntity<RepresentationModel> addLinesToTicketById(@PathVariable String id,@RequestParam(required = false) Integer line) {
        RepresentationModel result =representationConverterApi.toConvert(
                ticketService.addLinesToTicketById(id,Optional.ofNullable(line).orElse(1))
        );

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
    public HttpEntity<RepresentationModel> getStatusById(@PathVariable String id) {
        RepresentationModel result =representationConverterApi.toConvert(ticketService.getTicketStatusById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
