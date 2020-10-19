package cn.ann.part01._03linked.list;

/**
 * Description：动态数组接口
 * <p>
 * Date: 2020-8-29 18:28
 *
 * @author 88475
 * @version v1.0
 */
public interface List<E> {
    int ELEMENT_NOT_FOUNT = -1;

    /**
     * 查看元素的数量.
     *
     * @return size
     */
    int size();

    /**
     * 元素是否为空.
     *
     * @return true|false
     */
    boolean isEmpty();

    /**
     * 判断是否包含某个元素.
     *
     * @param element 要检测的元素
     * @return true|false
     */
    boolean contains(E element);

    /**
     * 添加元素到数组最后面.
     *
     * @param element 添加的元素
     */
    void add(E element);

    /**
     * 获取index位置的元素.
     *
     * @param index index
     * @return E
     */
    E get(int index);

    /**
     * 设置index位置的元素.
     *
     * @param index index
     * @param element element
     * @return element
     */
    E set(int index, E element);

    /**
     * 往index位置添加元素.
     *
     * @param index index
     * @param element element
     */
    void add(int index, E element);

    /**
     * 删除index位置对应的元素.
     *
     * @param index index
     * @return E 删除的元素
     */
    E remove(int index);

    /**
     * 删除元素 element
     *
     * @param element element
     */
    void remove(E element);

    /**
     * 查看元素的位置.
     *
     * @param element element
     * @return index 元素的索引
     */
    int indexOf(E element);

    /**
     * 清除所有元素.
     */
    void clear();
}
