package com.poppulo.hometest.lottery.controller;

import com.poppulo.hometest.lottery.converter.RepresentationConverterApi;
import com.poppulo.hometest.lottery.model.representation.TicketResponse;
import com.poppulo.hometest.lottery.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Create Ticket with line parameter or without")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ticket was created"),
            @ApiResponse(code = 500, message = "Unknown error"),
    })
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<RepresentationModel> createTicket(@RequestParam(required = false) Integer line) {
        RepresentationModel result =representationConverterApi.toConvert(
                ticketService.createTicket(Optional.ofNullable(line).orElse(1))
        );

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All tickets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tickets were returned"),
            @ApiResponse(code = 500, message = "Unknown error"),
    })
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Collection<RepresentationModel>> getAllTicket() {
        Collection<RepresentationModel> result =  representationConverterApi.toConvert(ticketService.getTickets());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Get ticket by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ticket was found"),
            @ApiResponse(code = 404, message = "Ticket was not found"),
            @ApiResponse(code = 500, message = "Unknown error"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<RepresentationModel> getTicketById(@PathVariable String id) {
        RepresentationModel result =representationConverterApi.toConvert(ticketService.getTicketById(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new lines to ticket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New lines were added"),
            @ApiResponse(code = 404, message = "Ticket was not found"),
            @ApiResponse(code = 400, message = "Ticket is locked"),
            @ApiResponse(code = 500, message = "Unknown error"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HttpEntity<RepresentationModel> addLinesToTicketById(@PathVariable String id,@RequestParam(required = false) Integer line) {
        RepresentationModel result =representationConverterApi.toConvert(
                ticketService.addLinesToTicketById(id,Optional.ofNullable(line).orElse(1))
        );

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Get status of ticket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ticket was returned"),
            @ApiResponse(code = 404, message = "Ticket was not found"),
            @ApiResponse(code = 500, message = "Unknown error"),
    })
    @RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
    public HttpEntity<RepresentationModel> getStatusById(@PathVariable String id) {
        RepresentationModel result =representationConverterApi.toConvert(ticketService.getTicketStatusById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
