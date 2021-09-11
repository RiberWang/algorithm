package com.rb;
import java.lang.Comparable;

public class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age) {
        this.age = age;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person person) {
//        if (age > person.age) return 1;
//        if (age < person.age) return -1;
//
//        return 0;
        return age - person.age;
    }

    @Override
    public String toString() {
        return "age=" + age + "_" + name;
    }
}
