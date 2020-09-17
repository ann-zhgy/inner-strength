package cn.ann.part01._06binary.tree;

/**
 * Description：
 * <p>
 * Date: 2020-9-3 17:33
 *
 * @author 88475
 * @version v1.0
 */
public abstract class AbstractBinaryTree<E> implements BinaryTree<E> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }
}
