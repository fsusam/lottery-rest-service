package com.poppulo.hometest.lottery.dto;

import java.util.Arrays;
import java.util.Objects;

public class TicketLine {
    private int[] numbers;
    private int result;

    public TicketLine(int[] numbers, int result) {
        this.numbers = numbers;
        this.result = result;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketLine that = (TicketLine) o;
        return result == that.result && Arrays.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        int result1 = Objects.hash(result);
        result1 = 31 * result1 + Arrays.hashCode(numbers);
        return result1;
    }
}
