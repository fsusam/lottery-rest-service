package com.poppulo.hometest.lottery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.poppulo.hometest.lottery.model.representation.TicketResponse;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketControllerIT {
    Logger logger = LoggerFactory.getLogger(TicketControllerIT.class);

    //@LocalServerPort
    //private int port;

    //@Autowired
    //private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private URI createURI(String url){
        try {
            return new URI("/tickets"+url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String createdTicketId;


    @Order(1)
    @Test
    void createTicketWithoutLineParam() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(createURI("")));
        createdTicketId = JsonPath.read(result.andReturn().getResponse().getContentAsString(), "$.ticketDto.ticketId");
        logger.info("Created ticket id : {}",createdTicketId);
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("ticketDto.lines", hasSize(1)));
        assertNotNull(createdTicketId);
    }

    @Order(2)
    @Test
    void createTicketWithLineParam() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(createURI("?line=2")));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("ticketDto.lines", hasSize(2)));
    }

    @Order(3)
    @Test
    void getTickets() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(createURI("")));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$", hasSize(2)));
    }

    @Order(4)
    @Test
    void getTicketById() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(createURI("/"+createdTicketId)));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.ticketDto.ticketId", is(createdTicketId)));
    }

    @Order(4)
    @Test
    void addLinesToTicketById() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(createURI("/"+createdTicketId+"?line=1")));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.ticketDto.lines", hasSize(2)));
    }

    @Order(5)
    @Test
    void getStatus() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(createURI("/"+createdTicketId+"/status")));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.ticketDto.ticketId", is(createdTicketId)));
        result.andExpect(jsonPath("$.ticketDto.locked", is(Boolean.TRUE)));
    }

    @Order(6)
    @Test
    void addLinesToLockedTicket() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(createURI("/"+createdTicketId+"?line=1")));
        String body = result.andReturn().getResponse().getContentAsString();
        result.andExpect(status().isBadRequest());
        assertEquals(body,"The ticket has been locked.");
    }

    @Order(6)
    @Test
    void getTicketNoDataFound() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(createURI("/test")));
        String body = result.andReturn().getResponse().getContentAsString();
        result.andExpect(status().isNotFound());
        assertEquals(body,"Ticket was not found");
    }
}
