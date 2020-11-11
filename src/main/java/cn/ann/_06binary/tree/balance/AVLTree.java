package cn.ann._06binary.tree.balance;

import java.util.Comparator;

/**
 * Description：平衡二叉树
 * <p>
 * Date: 2020-9-24 15:23
 *
 * @author 88475
 * @version v1.0
 */
public class AVLTree<E> extends AbstractBalancedBinaryTree<E> {
    public AVLTree() {
    }

    public AVLTree(Comparator<? super E> comparator) {
        super(comparator);
    }

    protected static class AVLNode<E> extends TreeNode<E> {
        int height = 1;

        AVLNode(E element, TreeNode<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
            int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
            int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public TreeNode<E> tallerChild() {
            int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
            int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    @Override
    protected void afterAdd(TreeNode<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 如果树平衡，就更新高度
                updateHeight(node);
            } else { // 如果不平衡，就恢复平衡
                rebalanced(node); // 恢复平衡的同时更新高度
                // 恢复平衡后，整棵树就平衡了，所以直接跳出
                break;
            }
        }
    }

    @Override
    protected void afterRemove(TreeNode<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 如果树平衡，就更新高度
                updateHeight(node);
            } else { // 如果不平衡，就恢复平衡
                rebalanced(node); // 恢复平衡的同时更新高度
                // 当前节点恢复平衡后，可能会导致上层的树不平衡，所以不能跳出
            }
        }
    }

    private void rebalanced(TreeNode<E> grand) { // node 就是节点 g
        TreeNode<E> parent = ((AVLNode<E>) grand).tallerChild();
        TreeNode<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isRightChild()) {// R
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) {// L
                rotateRight(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    public void afterRotate(TreeNode<E> grand, TreeNode<E> parent, TreeNode<E> child) {
        super.afterRotate(grand, parent, child);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    private void updateHeight(TreeNode<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private boolean isBalanced(TreeNode<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    @Override
    protected TreeNode<E> createNode(E element, TreeNode<E> parent) {
        return new AVLNode<>(element, parent);
    }
}
