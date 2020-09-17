package cn.ann.part01._03linked.list;

/**
 * Description：单向循环链表
 * <p>
 * Date: 2020-9-4 17:05
 *
 * @author 88475
 * @version v1.0
 */
public class SingleCircleLinkedList<E> extends AbstractList<E> {
    private Node<E> head;

    private static class Node<E> {
        E element;
        Node<E> next;

        Node() {
        }

        Node(E element) {
            this.element = element;
        }
    }

    public SingleCircleLinkedList() {
        head = new Node<>();
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);

        return node.element;
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

        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            head.next = newNode;
            newNode.next = newNode;
        } else {
            Node<E> node = node(index - 1);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node;
        if (index == 0) {
            head.next = head.next.next;
            node = node(size - 1);
        } else {
            node = node(index - 1);
        }
        E oldElement = node.next.element;
        node.next = node.next.next;
        size--;
        return oldElement;
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
        size = 0;
        head = null;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(node.element);
            node = node.next;
        }

        return builder.append("]").toString();
    }
}
