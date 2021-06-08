package com.poppulo.hometest.lottery.service;

import com.poppulo.hometest.lottery.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService{

//    @Autowired
//    private TicketNumberRepository ticketNumberRepository;

    @Override
    public String createTicket() {
//        Ticket ticket = new Ticket();
//        List<TicketNumber> numbers = new ArrayList<>();
//        TicketNumber ticketNumber = new TicketNumber();
//        ticketNumber.setTicket(ticket);
//        ticketNumber.setNumber(2);
//
//        numbers.add(ticketNumber);
//        ticket.setNumbers(numbers);
//
//        ticketNumberRepository.save(ticket);

        return "created ticket from Service";
    }

    @Override
    public String updateTicket() {
        return "updated ticket from Service";
    }

    @Override
    public List<TicketDto> getTickets() {
//        List<Ticket> tickets = ticketNumberRepository.findAll();
//        List<TicketDto> resultList = tickets.stream().map(TicketMapper::toDto).collect(Collectors.toList());
        return new ArrayList<>();
    }

    @Override
    public String getTicketById() {
        return "get ticket by Id from Service";
    }

    @Override
    public String getTicketStatusById() {
        return "get status of ticket from Service";
    }
}
