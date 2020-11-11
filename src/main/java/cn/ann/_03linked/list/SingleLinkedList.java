package cn.ann._03linked.list;

/**
 * Description：链表
 * <p>
 * Date: 2020-9-2 14:56
 *
 * @author 88475
 * @version v1.0
 */
public class SingleLinkedList<E> extends AbstractList<E> {

    private final Node<E> head;

    public SingleLinkedList() {
        head = new Node<>();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node() {
        }

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node == null ? null : node.element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        assert node != null;
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        Node<E> node;
        // 需要判断index为0的情况
        if (index == 0) {
            node = head;
        } else {
            // 获取到插入位置前一个结点
            node = node(index - 1);
        }
        assert node != null; // 仅仅是为了不报警告
        node.next = new Node<>(element, node.next);
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E element;
        if (index == 0) {
            // 判断index是0的情况
            element = head.next.element;
            head.next = head.next.next;
        } else {
            Node<E> node = node(index - 1);
            assert node != null;
            element = node.next.element;
            node.next = node.next.next;
        }
        size--;
        return element;
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
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(node.element.toString());
            node = node.next;
        }
        return builder.append("]").toString();
    }
}
