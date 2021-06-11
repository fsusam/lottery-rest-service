package com.poppulo.hometest.lottery.converter;

import com.poppulo.hometest.lottery.dto.Dto;
import com.poppulo.hometest.lottery.model.representation.TicketResponse;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;

public interface RepresentationConverterApi<T extends Dto> {
      public RepresentationModel toConvert(T dto);
      public Collection<RepresentationModel> toConvert(Collection<T> dto);
}
