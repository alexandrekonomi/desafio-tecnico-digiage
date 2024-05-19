package com.example;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TASK3Test extends TestCase {

    @Test
    public void testDistinctItems() {
        List<String> list = new ArrayList<String>();
        list.add("String1");
        list.add("String2");
        list.add("String3");
        list.add("String2");
        list.add("String1");

        Set<String> distinctItems = new HashSet<String>(list);

        assertEquals(3, distinctItems.size());
    }

    @Test
    public void testEmptyList() {
        List<String> list = new ArrayList<String>();
        Set<String> distinctItems = new HashSet<String>();

        assertEquals(0, distinctItems.size());
    }

    @Test
    public void testAllDistinctItems() {
        List<String> list = new ArrayList<String>();
        list.add("String1");
        list.add("String2");
        list.add("String3");
        list.add("String4");
        list.add("String5");

        Set<String> distinctItems = new HashSet<String>(list);

        assertEquals(5, distinctItems.size());
    }


}