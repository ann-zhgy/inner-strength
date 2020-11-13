package cn.ann._10binary.heap;

/**
 * Description：堆 接口定义
 * <p>
 * Date: 2020-11-13 14:14
 *
 * @author 88475
 * @version v1.0
 */
public interface Heap<E> {
    /**
     * 元素的数量
     *
     * @return size
     */
    int size();

    /**
     * 是否为空
     *
     * @return is empty
     */
    boolean isEmpty();

    /**
     * 清空堆
     */
    void clear();

    /**
     * 添加元素
     *
     * @param element element
     */
    void add(E element);

    /**
     * 获取堆顶元素
     *
     * @return element {@link E}
     */
    E get();

    /**
     * 删除堆顶元素
     *
     * @return element
     */
    E remove();

    /**
     * 删除堆顶元素的同时插入一个新元素
     * @param element 插入的元素
     * @return 删除的元素
     */
    E replace(E element);
}
