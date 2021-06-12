package com.poppulo.hometest.lottery.dao;

import com.poppulo.hometest.lottery.dto.TicketDto;
import com.poppulo.hometest.lottery.dto.TicketLine;

import java.util.Collection;
import java.util.List;

public interface LotteryDaoApi {
    public void saveOrUpdate(final TicketDto ticketDto);
    public Collection<TicketDto> getTickets();
    public TicketDto getTicketById(final String id);
}
