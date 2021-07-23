package com.rb;

/**
 * 动态缩容
 * @param <E>
 */
public class ArrayList2<E> extends AbstractList<E> {
    /*
    * 元素的数量
    * */
//    private int size;

    /*
    * 所有的元素
    * */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
//    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList2(int capaticy) {
        capaticy = (capaticy < 0) ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }

    public ArrayList2() {
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
        /**
         * 最好 O(1)
         * 最坏 O(n)
         * 平均 O(1) (1 + 1 + 1 + ... + n)
         * 均摊 O(1) 相当于每次add的操作是2
         * 均摊复杂度使用情况：经过连续的多次复杂度比较低的情况后，出现个别复杂度比较高的情况
         */
        add(size, element);
//        elements[size++] = element;
    }

    /*
    * 获取index位置的元素
    * */
    public E get(int index) { // O(1)
        rangeCheck(index);

        // index * 4 + 数组的首地址 根据地址定位元素位置
        return elements[index];
    }

    /*
    * 设置index位置的元素
    * */
    public E set(int index, E element) { // O(1)
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

        /**
         * 最好 O(1)
         * 最坏 O(n)
         * 平均 O(n)
         */
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        trim();

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
    public void add(int index, E element) { // O(n) n是数据规模
//        if (element == null) return;
        /**
         * 最好 O(1)
         * 最坏 O(n)
         * 平均 O(n) (0 + 1 + 2 + ... + n) / n = n*(n + 1) / (2*n) = (n + 1) / 2
         */
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

    /**
     * 动态缩容 设计的时候 扩容和缩容倍数相乘不等于1可以避免复杂度震荡
     */
    private void trim() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1; // 一半

        // 实际容量大于总容量的一半 并且 容量已经小于或等于默认容量
        if (size >= newCapacity || oldCapacity <= DEFAULT_CAPACITY) return;

        System.out.println(oldCapacity + "&" + newCapacity + "&" + size);

        // 剩余空间还有很多
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity + "缩容为" + newCapacity);
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
