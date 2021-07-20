package com.rb;

// 接口
public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;

    void clear();
    int size();
    boolean isEmpty();
    boolean contains(E element);
    void add(E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);
    void remove(E element);
    void add(int index, E element);

    int indexOf(E element);
}
