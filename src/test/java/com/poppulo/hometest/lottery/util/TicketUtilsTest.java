package com.poppulo.hometest.lottery.util;

import com.poppulo.hometest.lottery.dto.TicketLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;


class TicketUtilsTest {
    Logger logger = LoggerFactory.getLogger(TicketUtilsTest.class);

    private static final int UPPER_BOUND_RANDOM_VALUE = 3;
    private static final int LINE_NUMBER_SIZE = 3;

    @Test
    @DisplayName("Lines should be generated as per the given line count")
    void generateLines() {
        //logger.info("Random number : {}", randomNumberGenerator.generateNumber(3));
        List<TicketLine> ticketLines = TicketUtils.generateLines(3);
        assertNotNull(ticketLines);
        assertTrue(ticketLines.size()==3);
    }

    @Test
    @DisplayName("Length of Item of generated lines should be 3")
    void generateLinesTestItemLenghts() {
        List<TicketLine> ticketLines = TicketUtils.generateLines(2);
        assertNotNull(ticketLines);
        assertTrue(ticketLines.stream().filter(ticketLine ->
                ticketLine.getNumbers()!=null && ticketLine.getNumbers().length==3)
                .collect(Collectors.toList()).size()==2);
    }

    @Test
    @DisplayName("Unique Id which of length is 36 should be generated")
    void generateTicketId() {
        String id = TicketUtils.generateTicketId();
        assertNotNull(id);
        assertTrue(id.length()==36);
    }

    @Test
    @DisplayName("The result should be 10 when the sum of the numbers in the line is 2")
    void testResultSumOfNumbersInLineIsTwoResultTen() {
        //logger.info("Random number : {}", randomNumberGenerator.generateNumber(3));
        int[] expected = new int[] {0,1,1};

        MockedStatic<RandomNumberGenerator> randomNumberGeneratorMockedStatic =  Mockito.mockStatic(RandomNumberGenerator.class);
        randomNumberGeneratorMockedStatic.when(() -> RandomNumberGenerator.generateNumber(UPPER_BOUND_RANDOM_VALUE,LINE_NUMBER_SIZE))
                .thenReturn(expected);

        List<TicketLine> ticketLines = TicketUtils.generateLines(1);
        assertNotNull(ticketLines);
        assertTrue(Arrays.stream(ticketLines.get(0).getNumbers()).sum()==2);
        assertTrue(ticketLines.get(0).getResult()==10);
    }

    @Test
    @DisplayName("The result should be 5 when the sum of the numbers in the line is 0")
    void testSumOfNumbersInLineIsZeroResultFive() {
        //logger.info("Random number : {}", randomNumberGenerator.generateNumber(3));
        int[] expected = new int[] {0,0,0};

        try(MockedStatic<RandomNumberGenerator> randomNumberGeneratorMockedStatic =  Mockito.mockStatic(RandomNumberGenerator.class)){
            randomNumberGeneratorMockedStatic.when(() -> RandomNumberGenerator.generateNumber(UPPER_BOUND_RANDOM_VALUE,LINE_NUMBER_SIZE))
                    .thenReturn(expected);

            List<TicketLine> ticketLines = TicketUtils.generateLines(1);
            assertNotNull(ticketLines);
            assertTrue(Arrays.stream(ticketLines.get(0).getNumbers()).sum()==0);
            assertTrue(ticketLines.get(0).getResult()==5);
        }
    }

    @Test
    @DisplayName("The result should be 5 when the sum of the numbers in the line is 3")
    void testSumOfNumbersInLineIsThreeResultFive() {
        //logger.info("Random number : {}", randomNumberGenerator.generateNumber(3));
        int[] expected = new int[] {0,1,2};

        try(MockedStatic<RandomNumberGenerator> randomNumberGeneratorMockedStatic =  Mockito.mockStatic(RandomNumberGenerator.class)){
            randomNumberGeneratorMockedStatic.when(() -> RandomNumberGenerator.generateNumber(UPPER_BOUND_RANDOM_VALUE,LINE_NUMBER_SIZE))
                    .thenReturn(expected);

            List<TicketLine> ticketLines = TicketUtils.generateLines(1);
            assertNotNull(ticketLines);
            assertTrue(Arrays.stream(ticketLines.get(0).getNumbers()).sum()==3);
            assertTrue(ticketLines.get(0).getResult()==5);
        }
    }

    @Test
    @DisplayName("The result should be 5 when the sum of the numbers in the line is 6")
    void testSumOfNumbersInLineIsSixResultFive() {
        //logger.info("Random number : {}", randomNumberGenerator.generateNumber(3));
        int[] expected = new int[] {2,2,2};

        try(MockedStatic<RandomNumberGenerator> randomNumberGeneratorMockedStatic =  Mockito.mockStatic(RandomNumberGenerator.class)){
            randomNumberGeneratorMockedStatic.when(() -> RandomNumberGenerator.generateNumber(UPPER_BOUND_RANDOM_VALUE,LINE_NUMBER_SIZE))
                    .thenReturn(expected);

            List<TicketLine> ticketLines = TicketUtils.generateLines(1);
            assertNotNull(ticketLines);
            assertTrue(Arrays.stream(ticketLines.get(0).getNumbers()).sum()==6);
            assertTrue(ticketLines.get(0).getResult()==5);
        }
    }

    @Test
    @DisplayName("The result should be 1 when the second and third item of the numbers in the line are different from the first item")
    void testSecondAndThirdItemOfNumbersInLineAreDifferentFromFirstItemResultOne() {
        //logger.info("Random number : {}", randomNumberGenerator.generateNumber(3));
        int[] expected = new int[] {2,1,1};

        try(MockedStatic<RandomNumberGenerator> randomNumberGeneratorMockedStatic =  Mockito.mockStatic(RandomNumberGenerator.class)){
            randomNumberGeneratorMockedStatic.when(() -> RandomNumberGenerator.generateNumber(UPPER_BOUND_RANDOM_VALUE,LINE_NUMBER_SIZE))
                    .thenReturn(expected);

            List<TicketLine> ticketLines = TicketUtils.generateLines(1);
            int[] numbers = ticketLines.get(0).getNumbers();

            assertNotNull(ticketLines);
            assertTrue(ticketLines.get(0).getResult()==1);
        }
    }

    @Test
    @DisplayName("The result should be 0 when any rules is not fit")
    void testResutDefaultIsZero() {
        //logger.info("Random number : {}", randomNumberGenerator.generateNumber(3));
        int[] expected = new int[] {2,2,0};

        try(MockedStatic<RandomNumberGenerator> randomNumberGeneratorMockedStatic =  Mockito.mockStatic(RandomNumberGenerator.class)){
            randomNumberGeneratorMockedStatic.when(() -> RandomNumberGenerator.generateNumber(UPPER_BOUND_RANDOM_VALUE,LINE_NUMBER_SIZE))
                    .thenReturn(expected);

            List<TicketLine> ticketLines = TicketUtils.generateLines(1);
            int[] numbers = ticketLines.get(0).getNumbers();

            assertNotNull(ticketLines);
            assertTrue(ticketLines.get(0).getResult()==0);
        }
    }
}