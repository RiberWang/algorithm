package com.rb;

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

        testList(new ArrayList<>());
        testList(new LinkList<>());
    }

    static void testList(List<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55);
        list.add(2, 66);
        list.add(list.size(), 66);

        list.remove(0);
        list.remove(2);
        list.remove(list.size() - 1);

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) ==List.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 11);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);

        System.out.println(list);
    }
}
