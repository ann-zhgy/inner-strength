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

    /**
     * 遍历元素，默认使用前序遍历.
     *
     * @param visitor 设置怎么操作元素
     */
    void travarsal(Visitor<E> visitor);

    /**
     * 遍历元素.
     *
     * @param order 遍历方式
     * @param visitor visitor
     */
    void travarsal(TravarsalOrder order, Visitor<E> visitor);
}
