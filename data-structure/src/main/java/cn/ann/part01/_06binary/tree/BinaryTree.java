package cn.ann.part01._06binary.tree;

/**
 * Description：二叉树接口
 * <p>
 * Date: 2020-9-3 17:29
 *
 * @author 88475
 * @version v1.0
 */
public interface BinaryTree<E> {
    /**
     * 获取二叉树中有效数据的个数.
     *
     * @return size
     */
    int size();

    /**
     * 判断二叉树是否为空.
     *
     * @return true | false
     */
    boolean isEmpty();

    /**
     * 清空二叉树.
     */
    void clear();

    /**
     * 添加元素.
     *
     * @param element {@see E}
     */
    void add(E element);

    /**
     * 移除元素.
     *
     * @param element {@see E}
     */
    void remove(E element);

    /**
     * 是否包含元素.
     *
     * @param element {@see E}
     * @return true | false
     */
    boolean contains(E element);
}
