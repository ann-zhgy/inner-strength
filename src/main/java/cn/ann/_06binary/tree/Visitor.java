package cn.ann._06binary.tree;

/**
 * Description：Visitor
 * <p>
 * Date: 2020-9-19 0:14
 *
 * @author 88475
 * @version v1.0
 */
@FunctionalInterface
public interface Visitor<E> {
    /**
     * 因为不知道遍历方法的调用者要怎么操作元素，所以提供接口（策略模式）
     *
     * @param element elem
     * @return true：继续遍历 | false：结束遍历
     */
    boolean visit(E element);
}
