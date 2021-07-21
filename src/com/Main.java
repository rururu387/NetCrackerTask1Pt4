package com;

import org.w3c.dom.Text;

import javax.swing.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

//Task 1. Part 4.
public class Main
{
    private static void printLetterSet(Set<?> set)
    {
        Iterator<?> iterator = set.iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        //Subtask 3. Usage of functions example.
        //Example text placed here. No newlines added since content is not important.
        String text1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut" +
                " labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea" +
                " commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore " +
                "eu fugiat nulla pariatur.";
        Set<Character> intersectionSet = TextAnalysis.stringsLettersIntersection(text1, text2, new TextAnalysis.CounterAlphabetOrderComparator());
        System.out.println("Letters that appear in both strings. Placed in alphabet order. Uppercase first:");
        printLetterSet(intersectionSet);
        Set<Character> intersectionSet2 = TextAnalysis.stringsLettersIntersection(text1, text2, new TextAnalysis.HashShiftedASCOrderComparator(15));
        System.out.println("-------");
        System.out.println("Letters that appear in both strings. Placed in some strange hash-related order:");
        printLetterSet(intersectionSet2);
        System.out.println("It turns out that character's hashCode() returns ascii representation of contained letter");
        System.out.println("-------");

        Set<Character> differenceSet = TextAnalysis.stringLettersDifference(text2, text1, new TextAnalysis.CounterAlphabetOrderComparator());
        System.out.println("Letters that appear in the second but not the first string. Placed in counter-alphabet order:");
        printLetterSet(differenceSet);
        System.out.println("-------");

        Set<Character> unionSet = TextAnalysis.stringLettersUnion(text1, text2, new TextAnalysis.HashShiftedASCOrderComparator(31));
        System.out.println("Letters that appear in both strings. Placed in circulum-shifted hash order:");
        printLetterSet(unionSet);
        System.out.println("-------");
    }
}
