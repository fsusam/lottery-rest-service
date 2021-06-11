package com.poppulo.hometest.lottery.service;

import com.poppulo.hometest.lottery.dao.LotteryDaoApi;
import com.poppulo.hometest.lottery.dto.TicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private LotteryDaoApi lotteryDaoApi;

    Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public TicketDto createTicket(final int line) {
        logger.info(" line count is : {}",line);
        return lotteryDaoApi.createLines(line);
    }

    @Override
    public TicketDto updateTicket(final String id, final int line) {
        return lotteryDaoApi.addLinesToTicketById(id, line);
    }

    @Override
    public Collection<TicketDto> getTickets() {
        return lotteryDaoApi.getTickets();
    }

    @Override
    public TicketDto getTicketById(final String id) {
        logger.info("Ticket id : {}",id);
        return lotteryDaoApi.getTicketById(id);
    }

    @Override
    public TicketDto getTicketStatusById(final String id) {
        TicketDto ticketDto = lotteryDaoApi.getTicketById(id);
        if(!ticketDto.isLocked()){
            lotteryDaoApi.lockTicketById(id);
        }
        return ticketDto;
    }
}
