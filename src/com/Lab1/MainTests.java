package com.Lab1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.util.stream.Stream;

public class MainTests {
    @ParameterizedTest
    @MethodSource("provideInvalidIndexesForDeleteElementFromArray")
    void DeleteElementFromArray_InvalidIndexPassed_ThrowsOutOfBoundException(int index) {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            var arr = new int[]{1, 2, 3, 4, 5};

            Main.deleteElementFromArray(arr, index);
        });
    }

    private static Stream<Arguments> provideInvalidIndexesForDeleteElementFromArray() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(-2),
                Arguments.of(-3),
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(7)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void DeleteElementFrom_ValidIndexPassed_ReturnsCopyOfArray(int index) {
        var arr = new int[]{1, 2, 3, 4, 5};

        var result = Main.deleteElementFromArray(arr, index);
        assertNotSame(result, arr);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void DeleteElementFrom_ValidIndexPassed_DeletesElementAtIndex(int index) {
        var arr = new int[]{1, 2, 3, 4, 5};
        var elementAtIndex = arr[index];

        var result = Main.deleteElementFromArray(arr, index);
        assertNotEquals(elementAtIndex, result[index]);
    }

    @Test
    void FindLargestPalindromeInString_EmptyStringPassed_ReturnsEmptyString() {
        var result = Main.findLargestPalindromeInString("");

        assertEquals("", result);
    }

    @Test
    void FindLargestPalindromeInString_StringWithoutPalindromesPassed_ReturnsEmptyString() {
        var result = Main.findLargestPalindromeInString("notAPalindrome");

        assertEquals("", result);
    }

    @Test
    void FindLargestPalindromeInString_ValidStringPassed_ReturnsLargestPalindrome() {
        var result = Main.findLargestPalindromeInString("qweoloruaautoollooy");

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
    @ValueSource(strings = {"", "random text", "14-11-2015"})
    void CalculateDaysFromYearBeginTo_InvalidStringFormatPassed_ThrowsParseException(String dateString) {
        assertThrows(ParseException.class, () -> Main.calculateDaysFromYearBeginToDate(dateString));
    }

    // Test will break in the next year
    @Test
    void CalculateDaysFromYearBeginTo_ValidStringFormatPassed_ReturnsDaysPassedBetweenCurrentYearBeginAndDate() throws ParseException {
        var result = Main.calculateDaysFromYearBeginToDate("15.01.21");

        assertEquals(result, 14);
    }

    @Test
    void FindMinAmong_NoArgumentsPassed_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, Main::findMinAmong);
    }

    @Test
    void FindMinAmong_ValidArgumentsPassed_ReturnsMinValueAmongArrays() {
        var result = Main.findMinAmong(new int[]{1, 2, 3, 4 - 5, 1}, new int[]{10, 20, 100, -56, -320}, new int[]{-10, -33, 1058, 579});

        assertEquals(result, -320);
    }
}
