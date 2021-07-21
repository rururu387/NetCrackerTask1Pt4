package com;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

//Subtask 1.
public class Person
{
    LocalDate birthDay;

    public Person(LocalDate birthDay)
    {
        this.birthDay = birthDay;
    }


    //Date is formatted to string via formatter
    public String toString(DateTimeFormatter formatter)
    {
        return birthDay.format(formatter);
    }

    //Date is formatted using short, medium, long and full patterns
    public String toString(FormatStyle style)
    {
        return birthDay.format(DateTimeFormatter.ofLocalizedDate(style));
    }
}
