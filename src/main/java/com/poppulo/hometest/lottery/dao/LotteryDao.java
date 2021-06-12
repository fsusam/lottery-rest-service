package com.poppulo.hometest.lottery.dao;

import com.poppulo.hometest.lottery.dto.TicketDto;
import com.poppulo.hometest.lottery.dto.TicketLine;
import com.poppulo.hometest.lottery.exception.NoDataFoundException;
import com.poppulo.hometest.lottery.util.TicketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

@Repository
public class LotteryDao implements LotteryDaoApi {
    Logger logger = LoggerFactory.getLogger(LotteryDao.class);

    private Hashtable<String, TicketDto> ticketHashtable = new Hashtable<>();

    @Override
    public void saveOrUpdate(final TicketDto ticketDto) {
        ticketHashtable.put(ticketDto.getTicketId(),ticketDto);
    }

    @Override
    public Collection<TicketDto> getTickets() {
        return this.ticketHashtable.values();
    }

    @Override
    public TicketDto getTicketById(final String id) {
        TicketDto ticketDto = this.ticketHashtable.get(id);
        if (ticketDto==null)
            throw new NoDataFoundException("Ticket was not found");
        return this.ticketHashtable.get(id);
    }


}
