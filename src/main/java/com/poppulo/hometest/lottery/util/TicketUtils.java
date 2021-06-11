package com.poppulo.hometest.lottery.util;

import com.poppulo.hometest.lottery.dto.TicketLine;

import java.util.*;

public final class TicketUtils {
    private static final int UPPER_BOUND_RANDOM_VALUE = 3;
    private static final int LINE_NUMBER_SIZE = 3;

    private TicketUtils(){}

    public static List<TicketLine> generateLines(final int line) {
        List<TicketLine> lines = new ArrayList<>();
        for(int i =0; i<line; i++){
            int[] numbers = getRandomNumbers();
            int result = getLineResult(numbers);
            lines.add(new TicketLine(numbers,result));
        }
        return lines;
    }

    public static String generateTicketId(){
        return UUID.randomUUID().toString();
    }

    private static int[] getRandomNumbers(){
        Random rand = new Random();
        int[] numbers = new int[LINE_NUMBER_SIZE];
        for (int i=0; i<LINE_NUMBER_SIZE; i++)
            numbers[i] = rand.nextInt(UPPER_BOUND_RANDOM_VALUE);
        Arrays.sort(numbers);
        return numbers;
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
    /*public static void main(String[] args) {
        List<int[]> result = generateLines(10);

        result.forEach(numbers -> {
            System.out.println(Arrays.toString(numbers));
        });
    }*/
}
