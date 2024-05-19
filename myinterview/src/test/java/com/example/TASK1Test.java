package com.example;

import junit.framework.TestCase;
import org.junit.Test;

public class TASK1Test extends TestCase {
    @Test
    public void testPalindrome() {
        assertTrue(TASK1.isPalindrome("madam"));
        assertTrue(TASK1.isPalindrome("racecar"));
        assertTrue(TASK1.isPalindrome("121"));
        assertTrue(TASK1.isPalindrome("a"));
        assertTrue(TASK1.isPalindrome(""));
    }

    @Test
    public void testNotPalindrome() {
        assertFalse(TASK1.isPalindrome("hello"));
        assertFalse(TASK1.isPalindrome("word"));
        assertFalse(TASK1.isPalindrome("12345"));
        assertFalse(TASK1.isPalindrome("palindrome"));
    }
}