package com.rb;

import list.ArrayList;

// 继承 所有方法都会继承 不合理
public class Stack<E> extends ArrayList<E> {
    public void push(E element) {
        add(element);
    }

    public E pop() {
        return remove(size - 1);
    }

    public E top() {
        return get(size - 1);
    }
}
