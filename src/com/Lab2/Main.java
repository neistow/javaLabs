package com.Lab2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        taskOne();
        taskTwo();
        taskThree();
        taskFour();
    }

    // Продемонструйте у коді  2 способа  обробки виключних ситуацій у методі (з try-catch и  throws),
    // а також особливості використання блока finally. У якості обробляємо виключення
    // оберіть будь яке =checked= виключення.
    public static void taskOne() {
        try {
            methodOne();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Doing smth in try block");

            try {
                String s = null;
                s.length();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }

            return;
        } finally {
            System.out.println("Finally still executed");
        }
    }

    private static void methodOne() throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    // Продемонструйте у коді генерацію свого виключення AgeStudentException
    // у конструкторі класу Student при спробі створити об'єкт зі значенням віку помилковим.
    public static void taskTwo() {
        try {
            var student = new Student(1, "Foo");
        } catch (AgeStudentException e) {
            System.out.println(e);
        }
    }

    // Створіть просту  ієрархію спадкування, яка відповідає сутностям Товар (властивості - назва, виробник, дата виготовлення, ціна)
    // та Продукти харчування (термін придатності, харчова група (молочні, м'ясні, бакалея та інше)),
    // РадіоЕлектроніка (вид електроніки, гарантійний термін) Продемонструйте перевантаження конструкторів
    // та методів, перевизначення методів, особливості роботи конструкторів при спадкуванні.
    public static void taskThree() {
        var i = new Item("test",
                "foo",
                Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                20f);
        i.doSmth();

        var e = new Electronics("test",
                "foo",
                Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                40f,
                "bar");
        e.doSmth();
        e.doSmth("test");

        var p = new Product("test",
                "foo",
                Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                40f,
                Date.from(LocalDate.now().plusDays(100).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "some group");
    }

    // Створіть 3 різних реалізації інтерфейсу Comparator<String>. Перша реалізація повинна допомагати сортувати
    // рядки по довжині, друга - по 3 символу, третя- по кількості голосних букв англ. Алфавиту.
    // Продемонструйте використання усіх default-реалізацій методів цього інтерфейсу.
    public static void taskFour() {
        var list = Arrays.asList("cbodsa", "yddz", "aak");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(), o1.length());
            }
        });

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Character.compare(o1.charAt(3), o2.charAt(3));
            }
        });

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                var vowelsCount1 = countVowels(o1);
                var vowelsCount2 = countVowels(o2);

                return Integer.compare(vowelsCount1, vowelsCount2);
            }
        });
    }

    public static int countVowels(String word) {
        var vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        var result = 0;
        for (var c : word.toCharArray()) {
            result = vowels.contains(c) ? ++result : result;
        }

        return result;
    }
}
