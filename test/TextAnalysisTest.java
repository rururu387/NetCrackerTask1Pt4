import com.TextAnalysis;
import org.junit.Test;
import org.w3c.dom.Text;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TextAnalysisTest
{
    private final String text1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
            "ut labore et dolore magna aliqua.";
    private final String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo" +
            " consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla" +
            " pariatur.";

    @Test
    public void intersectionCounterAlphabeticTest1()
    {
        Set<Character> intersectionSetActual = TextAnalysis.stringsLettersIntersection(text1, text2, new TextAnalysis.CounterAlphabetOrderComparator());
        Set<Character> expected = new TreeSet<>();
        expected.addAll(List.of('u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'i', 'g', 'e', 'd', 'c', 'b', 'a', '.', ',', ' '));
        assertEquals(expected, intersectionSetActual);
    }

    @Test
    public void intersectionHashTest1()
    {
        Set<Character> intersectionSetActual = TextAnalysis.stringsLettersIntersection(text1, text2, new TextAnalysis.HashShiftedASCOrderComparator(15));
        Set<Character> expected = new TreeSet<>();
        expected.addAll(List.of(' ', ',', '.', 'a', 'b', 'c', 'd', 'e', 'g', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u'));
        assertEquals(expected, intersectionSetActual);
    }

    @Test
    public void differenceCounterAlphabeticTest1()
    {
        Set<Character> differenceSet = TextAnalysis.stringLettersDifference(text2, text1, new TextAnalysis.CounterAlphabetOrderComparator());
        Set<Character> expected = new TreeSet<>();
        expected.addAll(List.of('x', 'v', 'h', 'f', 'U', 'D'));
        assertEquals(expected, differenceSet);
    }

    //It turns out that Character.hashCode() returns ASCII (stored value) of character.
    //That's why letters here are placed in alphabetic-like order.
    //Thus, this test is easy to create.
    @Test
    public void unionHashShiftedTest1()
    {
        Set<Character> unionSet = TextAnalysis.stringLettersUnion(text1, text2, new TextAnalysis.HashShiftedASCOrderComparator(4));
        Set<Character> expected = new TreeSet<>();
        expected.addAll(List.of(' ', ',', '.', 'D', 'L', 'U', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x'));
        assertEquals(expected, unionSet);
    }

    @Test
    public void intersectionHashShiftedDemonstration()
    {

        //Check somehow here. It will take too much time to do it manually.
        //Order of letters is not alphabetic-like as letters are shfted 31 time.
        Set<Character> intersectionSet = TextAnalysis.stringsLettersIntersection(text1, text2, new TextAnalysis.HashShiftedASCOrderComparator(31));

        //This is how to reorder characters. This is an expensive operation.
        Set<Character> intersectionSetReordered = new TreeSet<>(new TextAnalysis.CounterAlphabetOrderComparator());
        intersectionSetReordered.addAll(intersectionSet);
        Iterator<?> iterator = intersectionSetReordered.iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}