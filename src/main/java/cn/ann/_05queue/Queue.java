package cn.ann._05queue;

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

    /**
     * 入队
     *
     * @param element element
     */
    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[index(size++)] = element;
    }

    /**
     * 出队
     *
     * @return element
     */
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

    /**
     * 查看队首元素
     *
     * @return element
     */
    public E front() {
        return elements[front];
    }

    /**
     * 是否包含元素
     *
     * @param element element
     * @return true|false
     */
    public boolean contains(E element) {
        if (element == null) {
            for (E e : elements) {
                if (e == null) {
                    return true;
                }
            }
        } else {
            for (E e : elements) {
                if (element.equals(e)) {
                    return true;
                }
            }
        }
        return false;
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
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
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
        throw new EmptyQueueException("队列空");
    }
}

