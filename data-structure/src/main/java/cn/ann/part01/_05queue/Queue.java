package cn.ann.part01._05queue;

import java.util.Arrays;

/**
 * Description：队列，基于数组
 * <p>
 * Date: 2020-9-13 21:33
 *
 * @author 88475
 */
@SuppressWarnings("unchecked")
public class Queue<E> {
    private int front;
    private int size;

    private E[] elements;

    private final int DEFAULT_CAPACITY = 10;

    public Queue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        front = 0;
        size = 0;
        Arrays.fill(elements, null);
    }

    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[index(size++)] = element;
    }

    public E deQueue() {
        if (isEmpty()) {
            queueEmpty();
        }
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    public E front() {
        return elements[front];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(String.format("Queue{front = %d, elements = [", front));
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(elements[index(i)]);
        }
        return builder.append("]}").toString();
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= this.elements.length) {
            return;
        }
        int newCapacity = capacity + (capacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < capacity; i++) {
            newElements[i] = elements[index(i)];
        }
        this.elements = newElements;
        front = 0;
    }

    private int index(int index) {
        index += front;
        /* 对获取索引方法的优化：不使用 % 运算
        if (index < 0) {
            return index + elements.length;
        }
        return index % elements.length;*/
        index -= index >= elements.length ? elements.length : 0;
        return index;
    }

    private void queueEmpty() {
        throw new RuntimeException("队列空");
    }
}
