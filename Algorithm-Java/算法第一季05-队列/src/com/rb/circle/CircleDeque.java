package com.rb.circle;

import list.LinkList;
import list.List;

// 循环双端队列
@SuppressWarnings("unchecked")
public class CircleDeque<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    /**
     * 队头入队
     * @param element
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 队尾入队
     * @param element
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 队头出队
     * @return
     */
    public E deQueueFront() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    /**
     * 队尾出队
     * @return
     */
    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E rear = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return rear;
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
            newElements[i] = elements[index(i)]; // 模
        }
        elements = newElements;

        // 重置front
        front = 0;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    public E front() {
        return elements[front];
    }

    public E rear() {
        return elements[index(size - 1)];
    }

    private int index(int index) {
        index += front;
        if (index < 0) {
            return index + elements.length;
        }
        // m > 0, n >= 0, n < 2m =>n % m = n - (n >= m ? m : 0)
        return index - (index >= elements.length ? elements.length : 0);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");

        return sb.toString();
    }
}
