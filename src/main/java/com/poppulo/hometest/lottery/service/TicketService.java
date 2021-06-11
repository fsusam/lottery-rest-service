package com.poppulo.hometest.lottery.service;

import com.poppulo.hometest.lottery.dto.TicketDto;

import java.util.Collection;
import java.util.List;

public interface TicketService {
    public TicketDto createTicket(final int line);
    public TicketDto updateTicket(final String id, final int line);
    public Collection<TicketDto> getTickets();
    public TicketDto getTicketById(final String id);
    public TicketDto getTicketStatusById(final String id);
}
