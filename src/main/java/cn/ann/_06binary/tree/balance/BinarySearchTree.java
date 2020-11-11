package cn.ann._06binary.tree.balance;

import cn.ann._06binary.tree.AbstractBinaryTree;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Description：二叉搜索树
 * <p>
 * Date: 2020-9-3 17:28
 *
 * @author 88475
 * @version v1.0
 */
public class BinarySearchTree<E> extends AbstractBinaryTree<E> {

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(E element) {
        checkElement(element);

        // 如果根节点为空，就添加到根节点
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        // 如果根节点不为空，先确定要插入到哪个位置
        TreeNode<E> parent = root;
        TreeNode<E> node = root;
        int compareResult = 0;

        try {
            while (node != null) {
                compareResult = compare(node.element, element);
                parent = node;
                if (compareResult > 0) {
                    node = node.left;
                } else if (compareResult < 0) {
                    node = node.right;
                } else {
                    // 如果比较结果相等，就用新值替换旧值
                    node.element = element;
                    return;
                }
            } // 查询出来之后，node就是要插入的位置（为null），parent是插入位置的父节点

            // 执行插入操作
            TreeNode<E> newNode = createNode(element, parent);
            if (compareResult > 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            size++;
            afterAdd(newNode);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(E element) {
        if (element == null) {
            return;
        }
        TreeNode<E> node = search(element);
        if (node == null) {
            throw new NoSuchElementException("元素未发现：" + element);
        }
        size--;
        // 节点度为2
        if (node.left != null && node.right != null) {
            // 找出后继节点
            TreeNode<E> successorNode = successor(node);
            node.element = successorNode.element;
            // 之后删除后继节点就好了
            node = successorNode;
        }
        // 到此，node 的度一定是 1 或 0
        // 如果 node 度为 1，replace 为非空的那个节点，如果 node 度为 0，replace 为 null
        TreeNode<E> replace = node.left != null ? node.left : node.right;
        if (replace != null) { // 度为 1，有子节点，需要让子节点的 parent 指向父节点；如果没有子节点的话就不用管了
            replace.parent = node.parent;
        }
        // node 是叶子节点
        if (node.parent == null) { // node 是根节点
            root = replace;
        } else { // node 不是根节点
            if (node == node.parent.left) {
                node.parent.left = replace;
            } else {
                node.parent.right = replace;
            }
        }
        // 删除之后的处理
        if (replace == null) { // 如果删除的是叶子节点，就传入删除的节点
            afterRemove(node);
        } else { // 如果删除的是度为1的节点，就传入替代的节点，这个操作是为了适配红黑树
            afterRemove(replace);
        }
    }

    /**
     * 添加完节点后要做什么：为AVL树和红黑树做准备
     *
     * @param node 添加的节点
     */
    protected void afterAdd(TreeNode<E> node) {
        /* 什么事情都不做，交给子类覆盖 */
    }

    /**
     * 删除节点后的处理：为AVL树和红黑树做准备
     *
     * @param node 删除的节点
     */
    protected void afterRemove(TreeNode<E> node) {
        /* 什么事情都不做，交给子类覆盖 */
    }

    /**
     * 创建要插入的节点，AVL树和红黑树只需要覆盖该方法就不用重新写 add 代码了，提高复用性
     *
     * @param element element
     * @param parent parent
     * @return treeNode
     */
    protected TreeNode<E> createNode(E element, TreeNode<E> parent) {
        return new TreeNode<>(element, parent);
    }
}
