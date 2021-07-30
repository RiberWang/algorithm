package com.rb;

import circle.CircleLinkedList;
import circle.SingleCircleLinkedList;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> list1 = new ArrayList<>();
//        if (list1.indexOf(20) == List.ELEMENT_NOT_FOUND) {
//
//        }

//        List<Integer> list = new LinkList2<>();
//        list.add(20);
//        list.add(0, 10);
//        list.add(30);
//        list.add(list.size(), 40);
//
//        list.remove(0);
//
//        System.out.println(list.indexOf(30));
//        System.out.println(list.get(1));
//
//        System.out.println(list);

//        List<Integer> list2 = new ArrayList2<>();
//        for (int i = 0; i < 50; i++) {
//            list2.add(i);
//        }
//
//        for (int i = 0; i < 50; i++) {
//            list2.remove(0);
//        }

//        testList(new ArrayList<>());
//        testList(new LinkList<>());
//        testList(new SingleCircleLinkedList<>());
        testList(new CircleLinkedList<>());

        System.out.println("双向循环链表：");
        josephus();
        System.out.println("\n单向循环链表：");
        josephusSingleCircleLinkedList();
    }

    static void josephusSingleCircleLinkedList() {
        SingleCircleLinkedList<Integer> list = new SingleCircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }

        // 指向头结点 指向1
        list.reset();

        while (!list.isEmpty()) {
            list.next();
            list.next();
            System.out.print(list.remove() + ", ");
        }
    }

    static void josephus() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }

        // 指向头结点 指向1
        list.reset();

        while (!list.isEmpty()) {
            list.next();
            list.next();
            System.out.print(list.remove() + ", ");
        }
    }

    static void testList(List<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55); // [55, 11, 22, 33, 44]
        list.add(2, 66); // [55, 11, 66, 22, 33, 44]
        list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

        list.remove(0); // [11, 66, 22, 33, 44, 77]
        list.remove(2); // [11, 66, 33, 44, 77]
        list.remove(list.size() - 1); // [11, 66, 33, 44]

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) ==List.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 11);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);

        System.out.println(list);
    }
}
