package com.poppulo.hometest.lottery.dao;

import com.poppulo.hometest.lottery.dto.TicketDto;

import java.util.Collection;
import java.util.List;

public interface LotteryDaoApi {
    public TicketDto createLines(final int line);
    public TicketDto addLinesToTicketById(final String id, final int line);
    public Collection<TicketDto> getTickets();
    public TicketDto getTicketById(final String id);
    public TicketDto lockTicketById(final String id);
}
