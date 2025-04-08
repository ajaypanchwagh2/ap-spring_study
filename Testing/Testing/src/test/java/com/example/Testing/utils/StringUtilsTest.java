package com.example.Testing.utils;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @org.testng.annotations.Test
    void testPalindromeTrue() {
        assertTrue(StringUtils.isPalindrome("121"));
    }

    @Test
    void testPalindromeFalse() {
        assertFalse(StringUtils.isPalindrome("hello"));
    }

    @Test
    void testRemoveSpaces() {
        String result = StringUtils.removeSpaces("Hello World");
        assertEquals("HelloWorld", result);
    }

    @Test
    void testRemoveSpaces_NoSpaces() {
        String result = StringUtils.removeSpaces("HelloWorld");
        assertEquals("HelloWorld", result);  // ✅ Passes
    }
}
