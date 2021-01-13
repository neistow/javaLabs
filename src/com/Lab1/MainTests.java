package com.Lab1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

public class MainTests {
    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void DeleteElementFrom_InvalidIndexPassed_ThrowsOutOfBoundException(int index) {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            var result = Main.deleteElementFrom(new int[]{1, 2, 3}, index);
        });
    }

    @Test
    void DeleteElementFrom_ValidIndexPassed_ReturnsCopyOfArray() {
        var arr = new int[]{1, 2, 3, 4, 5};

        var result = Main.deleteElementFrom(arr, 1);
        assertNotSame(result, arr);
    }

    @Test
    void DeleteElementFrom_ValidIndexPassed_DeletesElementAtIndex() {
        final var index = 1;
        var arr = new int[]{1, 2, 3, 4, 5};
        var elementAtIndex = arr[index];

        var result = Main.deleteElementFrom(arr, index);
        assertNotEquals(elementAtIndex, result[index]);
    }

    @Test
    void FindLargestPalindromeIn_EmptyStringPassed_ReturnsEmptyString() {
        var result = Main.findLargestPalindromeIn("");

        assertEquals("", result);
    }

    @Test
    void FindLargestPalindromeIn_StringWithoutPalindromesPassed_ReturnsEmptyString() {
        var result = Main.findLargestPalindromeIn("notAPalindrome");

        assertEquals("", result);
    }

    @Test
    void FindLargestPalindromeIn_ValidStringPassed_ReturnsLargestPalindrome() {
        var result = Main.findLargestPalindromeIn("qweoloruaautoollooy");

        assertEquals("oolloo", result);
    }

    @Test
    void Reverse_EmptyStringPassed_ReturnsEmptyString() {
        var result = Main.Reverse("");

        assertEquals("", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "someTest", "no?!t@t>?st", "35847"})
    void Reverse_StringPassed_ReturnsReversedString(String string) {
        var result = Main.Reverse(string);

        var expected = new StringBuilder(string).reverse().toString();
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "radnom text", "14-11-2015"})
    void CalculateDaysFromYearBeginTo_InvalidStringFormatPassed_ThrowsParseException(String dateString) {
        assertThrows(ParseException.class, () -> {
            var date = Main.calculateDaysFromYearBeginTo(dateString);
        });
    }

    // Test will break in the next year
    @Test
    void CalculateDaysFromYearBeginTo_ValidStringFormatPassed_ReturnsDaysPassedBetweenCurrentYearBeginAndDate() throws ParseException {
        var result = Main.calculateDaysFromYearBeginTo("15.01.21");

        assertEquals(result, 14);
    }

    @Test
    void FindMinAmong_NoArgumentsPassed_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            var result = Main.findMinAmong();
        });
    }

    @Test
    void FindMinAmong_ValidArgumentsPassed_ReturnsMinValueAmongArrays() {
        var result = Main.findMinAmong(new int[]{1, 2, 3, 4 - 5, 1}, new int[]{10, 20, 100, -56, -320}, new int[]{-10, -33, 1058, 579});

        assertEquals(result, -320);
    }
}
