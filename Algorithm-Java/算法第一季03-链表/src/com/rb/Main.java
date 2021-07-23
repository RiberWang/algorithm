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

        List<Integer> list2 = new ArrayList2<>();
        for (int i = 0; i < 50; i++) {
            list2.add(i);
        }

        for (int i = 0; i < 50; i++) {
            list2.remove(0);
        }
    }
}
