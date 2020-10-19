package cn.ann.part01._03linked.list;

/**
 * Description：双向链表实现
 * <p>
 * Date: 2020-9-4 15:10
 *
 * @author 88475
 * @version v1.0
 */
public class DoubleLinkedList<E> extends AbstractList<E> {

    private final Node<E> head;
    private final Node<E> tail;

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node() {
        }

        Node(E element) {
            this.element = element;
        }
    }

    public DoubleLinkedList() {
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        Node<E> node;
        Node<E> newNode = new Node<>(element);
        // 获取node节点
        if (index == size) {
            node = tail;
        } else {
            node = node(index);
        }
        // 向node节点的前面添加新节点
        newNode.prev = node.prev;
        newNode.next = node;
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node = node(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = head.next;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUNT;
    }

    @Override
    public void clear() {
        head.next = null;
        tail.prev = null;
        size = 0;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node;
        if (index >= size >> 1) {
            node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail.prev;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> node = head.next;
        for (int i = 0; i < size; i++, node = node.next) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(node.element);
        }

        return builder.append("]").toString();
    }
}
