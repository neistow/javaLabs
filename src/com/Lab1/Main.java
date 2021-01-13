package com.Lab1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws ParseException {
    }

    // Створіть метод, який дозволяє видаляти будь який елемент по індексу
    // в одновимірному масиві int[] Новий масив повинен повертатися з методу.
    public static int[] deleteElementFrom(int[] initialArray, int index) {
        if (index > initialArray.length || index < 0 || initialArray.length == 0) {
            throw new IndexOutOfBoundsException();
        }

        var newArray = new int[initialArray.length - 1];
        for (int i = 0; i < index; i++) {
            newArray[i] = initialArray[i];
        }

        for (int i = index + 1; i < initialArray.length; i++) {
            newArray[i - 1] = initialArray[i];
        }
        return newArray;
    }

    // Створіть метод, який приймає параметр типу String, та шукає
    // у його складі паліндроми(рядки букв, які однаково читаються
    // у любому напрямку - приклад  йцууцй)Метод повинен повертати паліндром ,
    // який має найбільшу довжину серед знайдених, якщо не знайдено ні одного,
    // повертає пустий рядок "". Приклад при параметрі "qwertyolo" результат буде "olo".
    public static String findLargestPalindromeIn(String str) {
        if (str.length() < 3) {
            return "";
        }

        var matchBegin = 0;
        var matchEnd = 0;

        for (int i = 0; i < str.length(); i++) {
            var index = str.lastIndexOf(str.charAt(i));
            if (index - i >= 2) {
                var substring = str.substring(i, index + 1);
                if (isPalindrome(substring) && substring.length() > matchEnd - matchBegin) {
                    matchBegin = i;
                    matchEnd = index + 1;
                }
            }
        }

        return str.substring(matchBegin, matchEnd);
    }

    private static boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    // Створіть метод, який перевертає будь яке слово.
    // Наприклад- на вході травень на виході ьневарт
    public static String Reverse(String str) {
        var builder = new StringBuilder(str.length());

        for (int i = str.length() - 1; i >= 0; i--) {
            builder.append(str.charAt(i));
        }

        return builder.toString();
    }

    // Створіть метод, який приймає параметр String у форматі "11.01.21"
    // та повертає кількість діб з початку року до цієї дати.
    // Ви повинні врахувати, що у лютому може бути різна кількість дат.
    public static long calculateDaysFromYearBeginTo(String dateString) throws ParseException {
        var formatter = new SimpleDateFormat("dd.MM.yy");

        var date = formatter.parse(dateString);

        var beginOfYearString = "01.01." + LocalDate.now().getYear();
        var beginOfYear = formatter.parse(beginOfYearString);

        var difference = Math.abs(date.getTime() - beginOfYear.getTime());
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

    // Створіть метод, який приймає будь-яку кількість масивів int[]
    // та повертає найменше число яке є у складі цих масивів
    public static int findMinAmong(int[]... arrays) {
        if (arrays.length == 0) {
            throw new IllegalArgumentException("At least one array should be provided");
        }

        var min = 0;
        for (var arr : arrays) {
            for (var number : arr) {
                if (number < min) {
                    min = number;
                }
            }
        }

        return min;
    }
}
