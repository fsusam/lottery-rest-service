package com.poppulo.hometest.lottery.dto;

import java.util.List;

public class TicketDto {
    private int ticketId;
    private List<Integer> numbers;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
