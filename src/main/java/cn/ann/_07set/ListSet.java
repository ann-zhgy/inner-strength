package cn.ann._07set;

import cn.ann._02array.list.ArrayList;
import cn.ann._03linked.list.List;
import cn.ann._06binary.tree.Visitor;

/**
 * Description：使用 list 实现的集合
 * <p>
 * Date: 2020-10-30 11:28
 *
 * @author 88475
 * @version v1.0
 */
public class ListSet<E> implements Set<E> {
    private final List<E> LIST = new ArrayList<>();

    @Override
    public int size() {
        return LIST.size();
    }

    @Override
    public boolean isEmpty() {
        return LIST.isEmpty();
    }

    @Override
    public void clear() {
        LIST.clear();
    }

    @Override
    public boolean contains(E element) {
        return LIST.contains(element);
    }

    @Override
    public void add(E element) {
        int index = LIST.indexOf(element);
        // 判断list中有没有该元素
        if (index == List.ELEMENT_NOT_FOUNT) {
            // 没有：添加
            LIST.add(element);
        } else {
            // 有：覆盖
            LIST.set(index, element);
        }
    }

    @Override
    public boolean remove(E element) {
        return LIST.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;
        for (int i = 0; i < LIST.size(); i++) {
            if (visitor.visit(LIST.get(i))) {
                break;
            }
        }

    }
}
