package com.poppulo.hometest.lottery.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TicketDto extends Dto {
    private String ticketId;
    private List<TicketLine> lines;
    private boolean locked = false;

    public TicketDto(String ticketId, List<TicketLine> lines) {
        this.ticketId = ticketId;
        this.lines = lines;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public List<TicketLine> getLines() {
        return lines;
    }

    public void setLines(List<TicketLine> lines) {
        this.lines = lines;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return Objects.equals(ticketId, ticketDto.ticketId) && Objects.equals(lines, ticketDto.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, lines);
    }

    @Override
    public String toString() {

        return "TicketDto{" +
                "ticketId='" + ticketId + '\'' +
                ", numbers=" + lines.stream().map(item -> Arrays.toString(item.getNumbers())).collect(Collectors.toList()) +
                '}';
    }
}
