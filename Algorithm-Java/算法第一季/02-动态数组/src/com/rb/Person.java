package com.rb;

import java.util.Objects;

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        System.out.println("Person finalize");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof Person) {
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
