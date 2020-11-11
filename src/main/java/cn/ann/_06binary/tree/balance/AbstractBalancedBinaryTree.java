package cn.ann._06binary.tree.balance;

import cn.ann._06binary.tree.AbstractBinaryTree;

import java.util.Comparator;

/**
 * Description：抽象平衡二叉树，提取了平衡二叉树的通用方法(旋转)
 * <p>
 * Date: 2020-10-10 11:09
 *
 * @author 88475
 * @version v1.0
 */
public abstract class AbstractBalancedBinaryTree<E> extends BinarySearchTree<E> {
    public AbstractBalancedBinaryTree() {
    }

    public AbstractBalancedBinaryTree(Comparator<? super E> comparator) {
        super(comparator);
    }

    protected void rotateRight(TreeNode<E> grand) {
        if (grand == null) {
            return;
        }
        AbstractBinaryTree.TreeNode<E> parent = grand.left;
        AbstractBinaryTree.TreeNode<E> child = parent.right;

        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateLeft(TreeNode<E> grand) {
        if (grand == null) {
            return;
        }
        AbstractBinaryTree.TreeNode<E> parent = grand.right;
        AbstractBinaryTree.TreeNode<E> child = parent.left;

        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    protected void afterRotate(TreeNode<E> grand, TreeNode<E> parent, TreeNode<E> child) {
        // 让 parent 成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        // 更新 child 的父节点
        if (child != null) {
            child.parent = grand;
        }

        // 更新 grand 的父节点
        grand.parent = parent;
    }
}
