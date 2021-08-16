package com.rb;
import com.rb.printer.BinaryTrees;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
//        test1();
        test2();

        /**
         * Java中的匿名类 类似于iOS的Block JS的闭包（function）
         */
        BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }
        });
        bst1.add(new Person(12));
        bst1.add(new Person(15));

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
        bst2.add(new Person(12));
        bst2.add(new Person(15));

        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator2());
        bst3.add(new Person(12));
        bst3.add(new Person(15));
    }

    static void test2() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(new Person(data[i]));
        };
        BinaryTrees.println(bst);

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        };
        BinaryTrees.println(bst2);
    }

    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        };

        BinaryTrees.print(bst, BinaryTrees.PrintStyle.LEVEL_ORDER);
    }

    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }}
