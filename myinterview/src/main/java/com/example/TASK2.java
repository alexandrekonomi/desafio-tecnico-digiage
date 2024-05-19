package com.example;

import java.util.List;

/**
 * Task here is to write a list. Each element must know the element before and
 * after it. Print out your list and them remove the element in the middle of
 * the list. Print out again.
 */


public class TASK2 {

    static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;
        int size = 0;

        // Add node to the end of the list
        void add(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            size++;
        }

        // Print the list
        void printList() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }

        // Remove the middle element
        void removeMiddle() {
            if (size == 0) return;

            Node middle = head;
            int midIndex = size / 2;

            for (int i = 0; i < midIndex; i++) {
                middle = middle.next;
            }

            if (middle.prev != null) {
                middle.prev.next = middle.next;
            } else {
                head = middle.next;
            }

            if (middle.next != null) {
                middle.next.prev = middle.prev;
            } else {
                tail = middle.prev;
            }

            size--;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("Original list:");
        list.printList();

        list.removeMiddle();

        System.out.println("List after removing the middle element:");
        list.printList();
    }
}
