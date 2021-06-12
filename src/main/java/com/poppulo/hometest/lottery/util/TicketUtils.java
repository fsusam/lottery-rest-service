package com.poppulo.hometest.lottery.util;

import com.poppulo.hometest.lottery.dto.TicketLine;

import java.util.*;

public class TicketUtils {
    private static final int UPPER_BOUND_RANDOM_VALUE = 3;
    private static final int LINE_NUMBER_SIZE = 3;

    TicketUtils(){}

    public static List<TicketLine> generateLines(final int line) {
        List<TicketLine> lines = new ArrayList<>();

        for(int i =0; i<line; i++){
            Random rand = new Random();
            int[] numbers = RandomNumberGenerator.generateNumber(UPPER_BOUND_RANDOM_VALUE,LINE_NUMBER_SIZE);
            int result = getLineResult(numbers);
            lines.add(new TicketLine(numbers,result));
        }

        lines.sort(Comparator.comparing(item -> item.getResult()));

        return lines;
    }

    public static String generateTicketId(){
        return UUID.randomUUID().toString();
    }

    private static int getLineResult(int[] numbers){
        int sum = Arrays.stream(numbers).sum();

        if(sum==2)
            return 10;
        else if(sum==0 || sum==3 || sum==6)
            return 5;
        else if(numbers[2]!=numbers[0] && numbers[1]!=numbers[0])
            return 1;
        else
            return 0;
    }

}
