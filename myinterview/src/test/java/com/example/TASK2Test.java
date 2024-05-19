package com.example;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TASK2Test extends TestCase {
    @Test
    public void testAddAndPrintList() {
        TASK2.DoublyLinkedList list = new TASK2.DoublyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.printList();

        String expectedOutput = "1 2 3 4 5 \n";
        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRemoveMiddle() {
        TASK2.DoublyLinkedList list = new TASK2.DoublyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.removeMiddle();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.printList();

        String expectedOutput = "1 2 4 5 \n";
        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRemoveMiddleSingleElement() {
        TASK2.DoublyLinkedList list = new TASK2.DoublyLinkedList();
        list.add(1);

        list.removeMiddle();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.printList();

        String expectedOutput = "";
        String actualOutput = outContent.toString().replace("\r\n", "");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRemoveMiddleEmptyList() {
        TASK2.DoublyLinkedList list = new TASK2.DoublyLinkedList();

        list.removeMiddle();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.printList();

        String expectedOutput = "";
        String actualOutput = outContent.toString().replace("\r\n", "");

        assertEquals(expectedOutput, actualOutput);
    }
}
