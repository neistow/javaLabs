package com.Lab2;

public class Student {
    private final int age;
    private final String name;

    public Student(int age, String name) throws AgeStudentException {
        this.name = name;
        if (age < 16 || age > 100) {
            throw new AgeStudentException(String.format("Student can't be %d years old", age));
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
