package com.rb;

import list.LinkList;
import list.List;

// 双端队列
public class Deque<E> {
    private List<E> list = new LinkList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }


    /**
     * 队头入队
     * @param element
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    /**
     * 队尾入队
     * @param element
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /**
     * 队头出队
     * @return
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 队尾出队
     * @return
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(list.size() - 1);
    }
}
