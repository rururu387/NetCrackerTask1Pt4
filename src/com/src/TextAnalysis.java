package com.src;

import com.sun.source.tree.Tree;

import java.util.*;

public class TextAnalysis
{
    public static Map<Character, Integer> getLetterFreq(String text)
    {
        Map<Character, Integer> letterFreqMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++)
        {

            //If map already has a record simply increment value
            if (letterFreqMap.containsKey(text.charAt(i)))
            {
                letterFreqMap.put(text.charAt(i), letterFreqMap.get(text.charAt(i)) + 1);
            }
            else
            {

                //Create a new record if not
                letterFreqMap.put(text.charAt(i), 1);
            }
        }
        return letterFreqMap;
    }

    public static Map<String, Integer> getWordFreq(String text)
    {
        Map<String, Integer> wordFreqMap = new HashMap<>();

        //Splitting string into words assuming that words consist of letters only
        String wordsArr[] = text.split("[^a-zA-Z]+");
        for (int i = 0; i < wordsArr.length; i++)
        {

            //If map already has a record value is incremented
            if (wordFreqMap.containsKey(wordsArr[i]))
            {
                wordFreqMap.put(wordsArr[i], wordFreqMap.get(wordsArr[i]) + 1);
            }
            else
            {

                //Create a new record if not
                wordFreqMap.put(wordsArr[i], 1);
            }
        }

        return wordFreqMap;
    }

    public static Set<Character> stringsLettersIntersection(String str1, String str2, Comparator comparator)
    {
        //Make sure that length of str1 > length of str2
        //This is a small, unnecessary optimization
        if (str1.length() < str2.length())
        {
            stringsLettersIntersection(str2, str1, comparator);
        }

        Set<Character> intersectionLetters = new TreeSet<>(comparator);

        Map<Character, Integer> freqMap = getLetterFreq(str2);

        for (int i = 0; i < str1.length(); i++)
        {
            if (freqMap.containsKey(str1.charAt(i)))
            {
                intersectionLetters.add(str1.charAt(i));
            }
        }

        return intersectionLetters;
    }

    public static Set<Character> stringLettersDifference(String source, String compareTo, Comparator comparator)
    {
        Set<Character> differenceLetters = new TreeSet<>(comparator);

        Map<Character, Integer> freqMap = getLetterFreq(compareTo);

        //For each letter from source check if compared string contains them.
        //Add letters to set if not.
        for (int i = 0; i < source.length(); i++)
        {
            if (!freqMap.containsKey(source.charAt(i)))
            {
                differenceLetters.add(source.charAt(i));
            }
        }

        return differenceLetters;
    }

    public static Set<Character> stringLettersUnion(String str1, String str2, Comparator comparator)
    {
        Set<Character> unionLetters = new TreeSet<>(comparator);

        for (int i = 0; i < str1.length(); i++)
        {
            unionLetters.add(str1.charAt(i));
        }

        for (int i = 0; i < str2.length(); i++)
        {
            unionLetters.add(str2.charAt(i));
        }

        return unionLetters;
    }

    //Casual order is an ambiguous definion. I made casual order the way it is described below:
    //Uppercase letters are considered to be greater than lowercase
    public static class AlphabetOrderComparator implements Comparator<Character>
    {
        @Override
        public int compare(Character o1, Character o2)
        {
            if (o1.equals(o2))
            {
                return 0;
            }

            //Making non-alphabetic characters appear last in our set
            if (Character.isAlphabetic(o1) != Character.isAlphabetic(o2))
            {
                if (Character.isAlphabetic(o1))
                {
                    return -1;
                }
                return 1;
            }

            if (o1 > o2)
            {
                return 1;
            }
            return -1;
        }
    }

    //This order is not strictly reverse, but I think that this order looks nicer.
    //Simply change signs of two return values inside if (Character.isALphabetic(o1) != ...) to make it reverse
    //Uppercase letters are considered to be less than lowercase
    public static class CounterAlphabetOrderComparator implements Comparator<Character>
    {
        @Override
        public int compare(Character o1, Character o2)
        {
            if (o1.equals(o2))
            {
                return 0;
            }

            //Make non-alphabetic characters appear last in our set
            if (Character.isAlphabetic(o1) != Character.isAlphabetic(o2))
            {
                if (Character.isAlphabetic(o1))
                {
                    return -1;
                }
                return 1;
            }

            if (o1 > o2)
            {
                return -1;
            }
            return 1;
        }
    }

    public static class HashShiftedASCOrderComparator implements Comparator<Character>
    {

        //Specifies amount of times that hash value is shifted before comparison
        int n;

        public HashShiftedASCOrderComparator(int n)
        {
            this.n = n;
        }

        //Default constructor is deleted
        private HashShiftedASCOrderComparator()
        {
        }

        @Override
        public int compare(Character o1, Character o2)
        {
            int shiftedHash1 = Integer.rotateLeft(o1.hashCode(), n);
            int shiftedHash2 = Integer.rotateLeft(o2.hashCode(), n);

            if (shiftedHash1 == shiftedHash2)
            {
                return 0;
            }
            if (shiftedHash1 > shiftedHash2)
            {
                return 1;
            }
            return -1;
        }
    }
}
