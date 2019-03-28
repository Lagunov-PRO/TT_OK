package ru.open.kzn.autotests;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        DateTimeFormatter month = DateTimeFormatter.ofPattern( "MM" );
        LocalDateTime todayDate = LocalDateTime.now();
        String todayMonth = todayDate.format(month);
        int todayMonthDigit = Integer.parseInt(todayMonth);

        List<Integer> monthsWithHeat = Arrays.asList(10, 11, 12, 1, 2, 3);

        if (monthsWithHeat.contains(todayMonthDigit)) {
            System.out.println("Account found");
        }


    }
}
