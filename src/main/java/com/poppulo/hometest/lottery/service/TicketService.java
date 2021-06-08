package com.poppulo.hometest.lottery.service;

import com.poppulo.hometest.lottery.dto.TicketDto;

import java.util.List;

public interface TicketService {
    public String createTicket();
    public String updateTicket();
    public List<TicketDto> getTickets();
    public String getTicketById();
    public String getTicketStatusById();
}
