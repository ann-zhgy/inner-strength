package cn.ann._07set;

import cn.ann._06binary.tree.BinaryTree;
import cn.ann._06binary.tree.Visitor;
import cn.ann._06binary.tree.balance.RedBlackTree;

import java.util.Comparator;

/**
 * Description：树实现的集合
 * <p>
 * Date: 2020-10-30 13:19
 *
 * @author 88475
 * @version v1.0
 */
public class TreeSet<E> implements Set<E> {
    private final BinaryTree<E> tree = new RedBlackTree<>();

    public TreeSet() {
    }

    public TreeSet(Comparator<E> comparator) {
        ((RedBlackTree<E>) tree).setComparator(comparator);
    }

    public void setComparator(Comparator<E> comparator) {
        ((RedBlackTree<E>) tree).setComparator(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public boolean remove(E element) {
        if (contains(element)) {
            tree.remove(element);
            return true;
        }
        return false;
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.traversal(visitor);
    }
}
