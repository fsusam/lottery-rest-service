package com.poppulo.hometest.lottery.converter;

import com.poppulo.hometest.lottery.dto.TicketDto;
import com.poppulo.hometest.lottery.model.representation.TicketResponse;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Component("ticketConverter")
public class TicketDtoConverter implements RepresentationConverterApi<TicketDto> {

    @Override
    public RepresentationModel toConvert(TicketDto ticketDto) {
        return new TicketResponse(ticketDto);
    }

    @Override
    public Collection<RepresentationModel> toConvert(Collection<TicketDto> dto) {
        Iterator<TicketDto> itr = dto.iterator();
        Collection<RepresentationModel> ticketResponses = new ArrayList<>();
        while (itr.hasNext()){
            TicketDto ticketDto = itr.next();
            ticketResponses.add(new TicketResponse(ticketDto));
        }
        return ticketResponses;
    }
}
