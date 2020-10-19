package cn.ann.part01._02array.list;

import cn.ann.part01._03linked.list.AbstractList;

import java.util.Arrays;

/**
 * Description：动态数组
 * <p>
 * Date: 2020-8-29 19:01
 *
 * @author 88475
 * @version v1.0
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {
    /** 数组 */
    private E[] elements;

    /** 默认初始化容量 */
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        // 用户可以指定初始化数组的容量，但是如果用户传入的是0，或者是负数，就按照默认大小初始化
        if (capacity > 0) {
            this.elements = (E[]) new Object[capacity];
        } else {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        elements[index] = element;
        return elements[index];
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 用户添加元素时，如果 数组的长度 和 size 相等，就需要扩容
        if (size == this.elements.length) {
            ensureCapacity(size + 1);
        }
        // 将 index 索引后面的元素右移
        /*
        for (int i = size; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }*/
        if (size - index >= 0) {
            System.arraycopy(this.elements, index, this.elements, index + 1, size - index);
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E element = this.elements[index];
        // 将删除位置后面的元素前移
        /*
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }*/
        if (size - 1 - index >= 0) {
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        }
        elements[--size] = null;
        return element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size - 1; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size - 1; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
//        for (int i = 0; i < size - 1; i++) {
//            if (element == null) {
//                if (elements[i] == null) {
//                    return i;
//                }
//            } else {
//                if (element.equals(elements[i])) {
//                    return i;
//                }
//            }
//        }

        return ELEMENT_NOT_FOUNT;
    }

    @Override
    public void clear() {
        // 将数组的每个值都置空，当JVM进行垃圾回收时，可以回收这些对象，释放内存
        /*
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }*/
        Arrays.fill(elements, null);
        size = 0;
    }

    /**
     * 确保数组的长度够用
     */
    private void ensureCapacity(int capacity) {
        // capacity 和 DEFAULT_CAPACITY 取最大值，可以防止创建ArrayList时设置 capacity 为 1 的情况
        capacity = Math.max(capacity, DEFAULT_CAPACITY);

        if (capacity < this.elements.length) {
            return;
        }
        int newCapacity = capacity + (capacity >> 1);
        E[] oldElements = this.elements;
        this.elements = (E[]) new Object[newCapacity];
        // Java中提供了高效的数组复制方法，所以就不用for循环了
        /*
        for (int i = 0; i < oldCapacity; i++) {
            this.elements[i] = oldElements[i];
        }*/
        System.arraycopy(oldElements, 0, this.elements, 0, oldElements.length);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(elements[i]);
        }
        return builder.append("]").toString();
    }
}
