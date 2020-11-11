package cn.ann._07set;

import cn.ann._06binary.tree.Visitor;

/**
 * Description：集合接口
 * <p>
 * Date: 2020-10-29 17:45
 *
 * @author 88475
 * @version v1.0
 */
public interface Set<E> {
    /**
     * 返回集合中的元素数量
     *
     * @return size
     */
    int size();

    /**
     * 集合是否为空
     *
     * @return true|false
     */
    boolean isEmpty();

    /**
     * 清空集合
     */
    void clear();

    /**
     * 是否包含某个元素
     *
     * @param element element
     * @return true|false
     */
    boolean contains(E element);

    /**
     * 向集合中添加元素
     *
     * @param element element
     */
    void add(E element);

    /**
     * 删除集合中的元素
     *
     * @param element element
     */
    boolean remove(E element);

    /**
     * 遍历
     *
     * @param visitor {@link Visitor}
     */
    void traversal(Visitor<E> visitor);
}
