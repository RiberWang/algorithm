package com.rb;

public class ArrayList {
    /*
    * 元素的数量
    * */
    private int size;

    /*
    * 所有的元素
    * */
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 5;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capaticy) {
        capaticy = (capaticy < 0) ? DEFAULT_CAPACITY : capaticy;
        elements = new int[capaticy];
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
    public boolean contains(int element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /*
    * 添加元素到尾部
    * */
    public void add(int element) {
        add(size, element);
//        elements[size++] = element;
    }

    /*
    * 获取index位置的元素
    * */
    public int get(int index) {
        rangeCheck(index);

        return elements[index];
    }

    /*
    * 设置index位置的元素
    * */
    public int set(int index, int element) {
        rangeCheck(index);

        int old = elements[index];
        elements[index] = element;
        return old;
    }

    /*
    * 删除index位置的元素
    * */
    public int remove(int index) {
        rangeCheck(index);

        int old = elements[index];
        for (int i = index + 1; i <= size - 1 ; i++) {
            elements[i - 1] = elements[i];
        }
        size--;

        return old;
    }

    /*
    * index位置插入一个元素
    * */
    public void add(int index, int element) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
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
        int[] newElements = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    /*
    * 查看元素的索引
    * */
    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) return i;
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
