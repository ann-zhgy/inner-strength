package cn.ann.part01._06binary.tree;

import java.util.Comparator;

/**
 * Description：二叉搜索树
 * <p>
 * Date: 2020-9-3 17:28
 *
 * @author 88475
 * @version v1.0
 */
public class _01BinarySearchTree<E> extends AbstractBinaryTree<E> {

    private Node<E> root;

    private final Comparator<E> comparator;

    public _01BinarySearchTree() {
        this(null);
    }

    public _01BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(E element) {
        // 如果根节点为空，就添加到根节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 如果根节点不为空
        // 先寻找要插入到那个位置
        Node<E> parent = root;
        Node<E> node = root;
        int compareResult = 0;
        while (node != null) {
            compareResult = compare(node.element, element);
            parent = node;
            if (compareResult > 0) {
                node = node.left;
            } else if (compareResult < 0) {
                node = node.right;
            } else {
                node.element = element;
                return;
            }
        } // 查询出来之后，node表示要插入的位置（为null），parent表示插入位置的父节点

        // 执行插入操作
        Node<E> newNode = new Node<>(element, parent);
        if (compareResult > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    @Override
    public void remove(E element) {

    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        if (comparator != null) { // 优先使用 comparator 进行比较
            return compare(e1, e2);
        } else if (e1 instanceof Comparable) {
            return ((Comparable<E>) e1).compareTo(e2);
        } else {
            throw new RuntimeException("请传入 Comparator 或设置实现了 Comparable 接口的元素类型");
        }
    }
}
