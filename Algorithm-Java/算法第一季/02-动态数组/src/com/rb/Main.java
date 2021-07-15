package com.rb;

public class Main {
    public static void main(String[] args) {
        // new是向堆空间申请内存
        ArrayList list = new ArrayList();
        list.add(99);
        list.add(88);
        list.add(11);
        list.add(20);
//        list.remove(0);
//        list.add(list.size(), 100);
//        list.set(2, 0);
//        if (list.get(2) != 0) {
//            throw  new IllegalArgumentException("测试不通过!");
//        }
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);


    }
}
