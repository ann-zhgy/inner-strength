package cn.ann._03linked.list;

/**
 * Description：抽象list
 * <p>
 * Date: 2020-9-2 15:00
 *
 * @author 88475
 * @version v1.0
 */
public abstract class AbstractList<E> implements List<E> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 检查用户传入的 index 是否有效，针对 add 方法
     * 因为用户可以在数组末尾添加元素，所以 index 可以等于 size
     *
     * @param index index
     */
    protected void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throwOutOfBound(index);
        }
    }

    /**
     * 检查用户传入的 index 是否有效
     *
     * @param index index
     */
    protected void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throwOutOfBound(index);
        }
    }

    private void throwOutOfBound(int index) {
        throw new IndexOutOfBoundsException("size: " + size + ", index: " + index);
    }
}
