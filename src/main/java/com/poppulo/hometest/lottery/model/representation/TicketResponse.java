package com.poppulo.hometest.lottery.model.representation;

import com.poppulo.hometest.lottery.controller.TicketController;
import com.poppulo.hometest.lottery.dto.TicketDto;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class TicketResponse extends RepresentationModel<TicketResponse> {
    private final TicketDto ticketDto;

    public TicketResponse(final TicketDto ticketDto){
        this.ticketDto = ticketDto;
        add(linkTo(methodOn(TicketController.class).getTicketById(this.ticketDto.getTicketId())).withSelfRel());
        add(linkTo(TicketController.class).withRel("tickets"));
        add(linkTo(methodOn(TicketController.class).addLinesToTicketById(this.ticketDto.getTicketId(),1)).withRel("addNewLine"));
        add(linkTo(methodOn(TicketController.class).getStatusById(this.ticketDto.getTicketId())).withRel("status"));
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

}
