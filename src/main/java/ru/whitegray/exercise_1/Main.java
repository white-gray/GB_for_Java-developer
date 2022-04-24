/*
Создан builder для класса Person со следующими полями:
        String firstName (обязательно),
        String lastName (обязательно),
        String phone (обязательно),
        String middleName,
        String country,
        String address,
        String age,
        String gender
* */

package ru.whitegray.exercise_1;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person.PersonBuilder("Gray", "White", "123-1234").build();
        System.out.println(person1.toString());
        Person person2 = new Person.PersonBuilder("Serg", "Bel", "123-1234")
                .country("Russia").age("42").build();
        System.out.println(person2.toString());
    }
}
