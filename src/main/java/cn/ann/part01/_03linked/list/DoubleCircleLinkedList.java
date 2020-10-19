package cn.ann.part01._03linked.list;

/**
 * Description：双向循环链表
 * <p>
 * Date: 2020-9-13 17:14
 *
 * @author 88475
 */
public class DoubleCircleLinkedList<E> extends AbstractList<E> {
    private final Node<E> head = new Node<>();

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node() {
        }

        public Node(E element) {
            this.element = element;
        }
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
        Node<E> newNode = new Node<>(element);
        if (head.next == null) {
            // 首次插入的情况
            head.next = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            Node<E> node;
            if (index == 0) {
                // 向头节点插入的情况
                node = head.next;
                head.next = newNode;
            } else {
                // 获取插入位置前的节点
                node = node(index - 1);
            }
            newNode.next = node.next;
            newNode.prev = node;
            node.next = newNode;
            newNode.next.prev = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node = node(index);
        if (index == 0) {
            head.next = node.next;
        }

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
        size = 0;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node;
        int mid = size >> 1;
        if (index < size) {
            node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = head.prev;
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
