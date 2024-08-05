package com.rental.app;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( String.format("$%d", 90) );
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate dueLocalDate = LocalDate.parse("12/20/24", datePattern);
    }
}
