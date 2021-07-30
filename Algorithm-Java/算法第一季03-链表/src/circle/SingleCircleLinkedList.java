package circle;

import com.rb.AbstractList;

/**
 * 单向链表
 * @param <E>
 */
public class SingleCircleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> current;

    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        }
        else {
            current = next;
        }

        return element;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public E get(int index) {
        /**
         * 最好 O(1)
         * 最坏 O(n)
         * 平均 O(n)
         */
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        /**
         * 最好 O(1)
         * 最坏 O(n)
         * 平均 O(n)
         */
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        /**
         * 最好 O(1)
         * 最坏 O(n)
         * 平均 O(n)
         */
        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            }
            else {
                Node<E> last = node(size - 1);
                first = first.next;
                last.next = first;
            }
        }
        else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;

        return node.element;
    }

    private E remove(Node<E> node) {
        if (node == first) {
            if (size == 1) {
                first = null;
            }
            else {
                Node<E> last = node(size - 1);
                first = first.next;
                last.next = first;
            }
        }
        else {
            Node<E> prev = node(indexOf(node.element) - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;

        return node.element;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        /**
         * 最好 O(1)
         * 最坏 O(n)
         * 平均 O(n)
         */
        if (index == 0) {
            Node<E> newFirst = new Node<>(element, first);
            // 拿到最后一个节点
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        }
        else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对应的节点对象
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element).append("_").append(next.element);
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node);
            node = node.next;
        }
        string.append("]");


        return string.toString();
    }
}
