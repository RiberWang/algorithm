package com.rb;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> list1 = new ArrayList<>();
//        if (list1.indexOf(20) == List.ELEMENT_NOT_FOUND) {
//
//        }
        List<Integer> list = new LinkList<>();
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size(), 40);

        list.remove(1);
        System.out.println(list);
    }
}
