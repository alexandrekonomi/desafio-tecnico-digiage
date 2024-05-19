package com.example;

import java.util.*;

/**
 * Write a list and add an aleatory number of Strings. In the end, print out how
 * many distinct itens exists on the list.
 */
public class TASK3 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        Random random = new Random();

        int numberOfElements = random.nextInt(20) + 1;
        for (int i = 0; i < numberOfElements; i++) {
            list.add("String" + random.nextInt(10));
        }

        System.out.println("List: " + list);

        Set<String> distinctItems = new HashSet<String>(list);

        System.out.println("Number of distinct items: " + distinctItems.size());
    }

}
