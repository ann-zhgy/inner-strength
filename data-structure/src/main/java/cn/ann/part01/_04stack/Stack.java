package cn.ann.part01._04stack;

import cn.ann.part01._02array.list.ArrayList;

/**
 * Description：栈，基于动态数组
 * <p>
 * Date: 2020-9-13 18:56
 *
 * @author 88475
 */
public class Stack<E> {
    private final ArrayList<E> stack = new ArrayList<>();

    /**
     * 压栈
     *
     * @param ele 压入栈的元素
     */
    public void push(E ele) {
        stack.add(ele);
    }

    /**
     * 弹栈
     *
     * @return 弹出的元素
     */
    public E pop() {
        return stack.remove(stack.size() - 1);
    }

    /**
     * 栈是否空
     *
     * @return true|false
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * 栈的大小
     *
     * @return int
     */
    public int size() {
        return stack.size();
    }

    /**
     * 获取栈顶部元素
     *
     * @return ele {@link E}
     */
    public E top() {
        return stack.get(stack.size() - 1);
    }
}
