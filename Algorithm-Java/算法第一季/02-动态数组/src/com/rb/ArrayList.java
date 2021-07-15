package com.rb;

public class ArrayList<E> {
    /*
    * 元素的数量
    * */
    private int size;

    /*
    * 所有的元素
    * */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capaticy) {
        capaticy = (capaticy < 0) ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /*
    * 清除所有元素
     * */
    public void clear() {
//        if (size < 100) {
//
//        }
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /*
    * 元素的数量
    * */
    public int size() {
        return size;
    }

    /*
    * 数组是否为空
    * */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    * 是否包含某个元素
    * */
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /*
    * 添加元素到尾部
    * */
    public void add(E element) {
        add(size, element);
//        elements[size++] = element;
    }

    /*
    * 获取index位置的元素
    * */
    public E get(int index) {
        rangeCheck(index);

        return elements[index];
    }

    /*
    * 设置index位置的元素
    * */
    public E set(int index, E element) {
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /*
    * 删除index位置的元素
    * */
    public E remove(int index) {
        rangeCheck(index);

        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        return old;
    }

    /*
     * 删除元素
     * */
    public void remove(E element) {
        remove(indexOf(element));
    }

    /*
    * index位置插入一个元素
    * */
    public void add(int index, E element) {
//        if (element == null) return;

        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        // 减1操作优化
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /*
    * 保证要有capacity的容量
    * */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1); // + 1 一般乘上一个系数
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    /*
    * 查看元素的索引
    * */
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) { // 此处index可以等于size
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
