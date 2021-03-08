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
    public static int[] deleteElementFromArray(int[] arr, int index) {
        if (index > arr.length || index < 0 || arr.length == 0) {
            throw new IndexOutOfBoundsException();
        }

        var newArray = new int[arr.length - 1];
        System.arraycopy(arr, 0, newArray, 0, index);

        if (arr.length - (index + 1) >= 0) {
            System.arraycopy(arr, index + 1, newArray, index + 1 - 1, arr.length - (index + 1));
        }

        return newArray;
    }

    // Створіть метод, який приймає параметр типу String, та шукає
    // у його складі паліндроми(рядки букв, які однаково читаються
    // у любому напрямку - приклад  йцууцй)Метод повинен повертати паліндром ,
    // який має найбільшу довжину серед знайдених, якщо не знайдено ні одного,
    // повертає пустий рядок "". Приклад при параметрі "qwertyolo" результат буде "olo".
    public static String findLargestPalindromeInString(String str) {
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
    public static long calculateDaysFromYearBeginToDate(String dateString) throws ParseException {
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
