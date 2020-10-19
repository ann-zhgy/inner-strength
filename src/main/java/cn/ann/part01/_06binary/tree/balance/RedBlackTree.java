package cn.ann.part01._06binary.tree.balance;

import java.util.Comparator;

/**
 * Description：红黑树实现
 * <p>
 * Date: 2020-10-10 10:48
 *
 * @author 88475
 * @version v1.0
 */
public class RedBlackTree<E> extends AbstractBalancedBinaryTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree() {
    }

    public RedBlackTree(Comparator<? super E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(TreeNode<E> node) {
        RedBlackNode<E> parent = (RedBlackNode<E>) node.parent;

        // 添加的是根节点或者上溢到了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) {
            return;
        }

        // 叔父节点
        TreeNode<E> uncle = parent.sibling();
        // 祖父节点(染红)
        TreeNode<E> grand = red(parent.parent);

        // 如果叔父节点是红色——B树节点上溢
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 祖父节点添加到B树父节点中
            afterAdd(grand);
            return;
        }
        // 叔父节点是黑色
        // L
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                black(parent);
            }
            // LR
            else {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }
        // R
        else {
            // RL
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            }
            // RR
            else {
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(TreeNode<E> node) {
        // 如果删除的是红色节点
        // 或者替代node的节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        TreeNode<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点，一定是【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        // 如果删除的节点是父节点的左子节点，那么其兄弟节点就是父节点的右子节点，反之亦然
        TreeNode<E> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更新现在的兄弟节点
                sibling = parent.right;
            }

            // 兄弟节点为黑色
            // 兄弟节点没有红色子节点：下溢，父节点向下合并
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentIsBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentIsBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟有红色节点，向兄弟借
                // 如果兄弟的红色节点在右边，兄弟需要先旋转
                if (isRed(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更新现在的兄弟节点
                sibling = parent.left;
            }

            // 兄弟节点为黑色
            // 兄弟节点没有红色子节点：父节点向下合并
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentIsBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentIsBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟有红色节点，向兄弟借
                // 如果兄弟的红色节点在右边，兄弟需要先旋转
                if (isRed(sibling.right)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    @Override
    protected TreeNode<E> createNode(E element, TreeNode<E> parent) {
        return new RedBlackNode<>(element, parent);
    }

    private TreeNode<E> red(TreeNode<E> node) {
        color(node, RED);
        return node;
    }

    private void black(TreeNode<E> node) {
        color(node, BLACK);
    }

    private void color(TreeNode<E> node, boolean color) {
        if (node == null) {
            return;
        }
        ((RedBlackNode<E>) node).color = color;
    }

    private boolean colorOf(TreeNode<E> node) {
        return node == null ? BLACK : ((RedBlackNode<E>) node).color;
    }

    private boolean isBlack(TreeNode<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(TreeNode<E> node) {
        return colorOf(node) == RED;
    }

    private static class RedBlackNode<E> extends TreeNode<E> {
        boolean color = RED;

        public RedBlackNode(E element, TreeNode<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            return element + (this.color == RED ? "_red" : "");
        }
    }

}
