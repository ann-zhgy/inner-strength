package cn.ann._10binary.heap;

import java.util.Comparator;

/**
 * Description：二叉堆
 * <p>
 * Date: 2020-11-9 18:01
 *
 * @author 88475
 * @version v1.0
 */
@SuppressWarnings("unchecked")
public abstract class AbstractHeap<E> implements Heap<E> {
    protected int size;
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1, E e2) throws NoSuchMethodException {
        if (comparator != null) { // 优先使用传入的 comparator 进行比较
            return comparator.compare(e1, e2);
        } else if (e1 instanceof Comparable) { // 其次使用泛型 E 自带的比较方法
            return ((Comparable<E>) e1).compareTo(e2);
        } else { // 如果没有传入比较器，泛型也没有比较方法，就抛异常
            throw new NoSuchMethodException("传入泛型未实现 Comparable 接口");
        }
    }
}
