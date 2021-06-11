package com.poppulo.hometest.lottery.dao;

import com.poppulo.hometest.lottery.dto.TicketDto;
import com.poppulo.hometest.lottery.util.TicketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

@Repository
public class LotteryDao implements LotteryDaoApi {
    Logger logger = LoggerFactory.getLogger(LotteryDao.class);

    private Hashtable<String, TicketDto> ticketHashtable = new Hashtable<>();

    @Override
    public TicketDto createLines(int line) {
        String ticketId = TicketUtils.generateTicketId();
        TicketDto ticketDto = new TicketDto(ticketId,TicketUtils.generateLines(line));
        ticketHashtable.put(ticketId,ticketDto);
        logger.info("hashtable : {}",ticketHashtable.get(ticketId));
        return ticketDto;
    }

    @Override
    public TicketDto addLinesToTicketById(final String id, final int line) {
        TicketDto ticketDto = getTicketById(id);
        if(!ticketDto.isLocked()){
            ticketDto.getLines().addAll(TicketUtils.generateLines(line));
            ticketHashtable.put(id,ticketDto);
        }
        return ticketDto;
    }

    @Override
    public Collection<TicketDto> getTickets() {
        return this.ticketHashtable.values();
    }

    @Override
    public TicketDto getTicketById(final String id) {
        return this.ticketHashtable.get(id);
    }

    @Override
    public TicketDto lockTicketById(String id) {
        TicketDto ticketDto = getTicketById(id);
        ticketDto.setLocked(Boolean.TRUE);
        ticketHashtable.put(id,ticketDto);
        return  ticketDto;
    }
}
