package com.poppulo.hometest.lottery.service;

import com.poppulo.hometest.lottery.dao.LotteryDaoApi;
import com.poppulo.hometest.lottery.dto.TicketDto;
import com.poppulo.hometest.lottery.exception.LockedTicketException;
import com.poppulo.hometest.lottery.util.TicketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private LotteryDaoApi lotteryDaoApi;

    Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public TicketDto createTicket(final int line) {
        logger.info("Input line : {}",line);
        TicketDto ticketDto =new TicketDto(TicketUtils.generateTicketId(),TicketUtils.generateLines(line));
        logger.info("Created ticket : {}",ticketDto);
        lotteryDaoApi.saveOrUpdate(ticketDto);
        return ticketDto;
    }

    @Override
    public TicketDto addLinesToTicketById(final String id, final int line) {
        TicketDto ticketDto = lotteryDaoApi.getTicketById(id);

        if(ticketDto.isLocked())
            throw new LockedTicketException("The ticket has been locked.");

        ticketDto.getLines().addAll(TicketUtils.generateLines(line));
        lotteryDaoApi.saveOrUpdate(ticketDto);

        return ticketDto;
    }

    @Override
    public Collection<TicketDto> getTickets() {
        return lotteryDaoApi.getTickets();
    }

    @Override
    public TicketDto getTicketById(final String id) {
        return lotteryDaoApi.getTicketById(id);
    }

    @Override
    public TicketDto getTicketStatusById(final String id) {
        TicketDto ticketDto = lotteryDaoApi.getTicketById(id);
        if(!ticketDto.isLocked()){
            ticketDto.setLocked(Boolean.TRUE);
            lotteryDaoApi.saveOrUpdate(ticketDto);
        }
        return ticketDto;
    }
}
