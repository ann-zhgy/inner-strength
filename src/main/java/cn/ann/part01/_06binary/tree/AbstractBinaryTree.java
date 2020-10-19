package cn.ann.part01._06binary.tree;

import cn.ann.part01._06binary.tree.util.BinaryTreeInfo;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.Stack;

/**
 * Description：二叉树抽象
 * <p>
 * Date: 2020-9-3 17:33
 *
 * @author 88475
 * @version v1.0
 */
@SuppressWarnings("unchecked")
public abstract class AbstractBinaryTree<E> implements BinaryTree<E>, BinaryTreeInfo {
    protected int size;
    protected TreeNode<E> root;
    protected Comparator<? super E> comparator;

    protected static class TreeNode<E> {
        public E element;
        /**
         * 此节点是否被访问过，专门为实现非递归方式的后序遍历添加的属性
         */
        public boolean isVisited;
        public TreeNode<E> parent;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E element, TreeNode<E> parent) {
            this.element = element;
            this.parent = parent;
            isVisited = false;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public TreeNode<E> sibling() {
            if (this.isLeftChild()) {
                return this.parent.right;
            }
            if (this.isRightChild()) {
                return this.parent.left;
            }
            return null;
        }

        @Override
        public String toString() {
            return "element=" + element;
        }
    }

    @Override
    public void travarsal(Visitor<E> visitor) {
        travarsal(TravarsalOrder.PREORDER, visitor);
    }

    @Override
    public void travarsal(TravarsalOrder order, Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        if (TravarsalOrder.PREORDER.equals(order)) {
            preorderTravarsal(root, visitor);
        } else if (TravarsalOrder.INORDER.equals(order)) {
            inorderTravarsal(root, visitor);
        } else if (TravarsalOrder.POSTORDER.equals(order)) {
            postorderTravarsal(root, visitor);
        } else if (TravarsalOrder.LEVELORDER.equals(order)) {
            levelorderTravarsal(root, visitor);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* 前序遍历：递归实现
    private void preorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        visitor.visit(node.element);
        preorderTravarsal(node.left, visitor);
        preorderTravarsal(node.right, visitor);
    }*/

    /**
     * 前序遍历，循环 + 栈
     *
     * @param node    node
     * @param visitor visitor
     */
    private void preorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> curr = node;
        // 因为前序遍历最后访问的一定是右子节点，所以可以使用这个条件
        // 当弹出最后一个节点后，curr = curr.right 可以保证 curr = null
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                if (!visitor.visit(curr.element)) {
                    return;
                }
                stack.push(curr);
                curr = curr.left;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                curr = curr.right;
            }
        }
    }

    /* 中序遍历：递归
    private void inorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        preorderTravarsal(node.left, visitor);
        visitor.visit(node.element);
        preorderTravarsal(node.right, visitor);
    }*/

    /**
     * 中序遍历：循环 + 栈
     *
     * @param node    node
     * @param visitor visitor
     */
    private void inorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> curr = node;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                if (!visitor.visit(curr.element)) {
                    return;
                }
                curr = curr.right;
            }
        }
    }

    /* 后序遍历：递归
    private void postorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        preorderTravarsal(node.left, visitor);
        preorderTravarsal(node.right, visitor);
        visitor.visit(node.element);
    }*/

    /**
     * 后序遍历：循环 + 栈
     *
     * @param node    node
     * @param visitor visitor
     */
    private void postorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> curr = node;
        while (curr != null) {
            // 走到curr节点下的最左端
            while (curr != null && !curr.isVisited) {
                stack.push(curr);
                curr = curr.left;
            }
            // 栈非空
            if (!stack.isEmpty()) {
                curr = stack.peek();
                // 当前节点被访问的前提是 没有右子树 或 右子树被访问过
                if (curr.right != null && !curr.right.isVisited) {
                    curr = curr.right;
                } else {
                    curr = stack.pop();
                    if (!visitor.visit(curr.element)) {
                        return;
                    }
                    curr.isVisited = true;
                }
            } else { // 如果栈空，跳出循环
                // 注意，这个不能写到上面的 while 循环的上面
                break;
            }
        }
    }
    /* TreeNode 节点中没有是否已访问的标记的情况
    private void postorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> curr = node;
        TreeNode<E> lastVisitNode = null;
        // 走到curr节点下的最左端
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        while (!stack.isEmpty()) {
            curr = stack.peek();
            // 当前节点被访问的前提是 没有右子树 或 右子树被访问过
            if (curr.right != null && curr.right != lastVisitNode) {
                // 当前节点不能被访问，即：有右子树，且右子树没有被访问过
                curr = curr.right;
                // 走到右子树的最左端
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            } else {
                // 当前节点可以被访问
                curr = stack.pop();
                if (!visitor.visit(curr.element)) {
                    return;
                }
                lastVisitNode = curr;
            }
        }
    }*/

    /**
     * 层序遍历（广度优先遍历）：循环 + 队列
     *
     * @param node    node
     * @param visitor visitor
     */
    private void levelorderTravarsal(TreeNode<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            TreeNode<E> treeNode = queue.poll();
            boolean visit = visitor.visit(treeNode.element);
            if (!visit) {
                break;
            }
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }
    }

    /**
     * 获取树的高度
     *
     * @return height
     */
    public int height() {
        return height(root);
    }

    /**
     * 获取节点的高度，循环实现
     *
     * @param node node
     * @return height
     */
    private int height(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }

        // 实现思路：假设我们当前遍历的是第 i 层，当我们遍历完第 i 层之后，队列中的元素数量就是 i + 1 层的结点的数量
        int height = 0;
        int levelSize = 1;
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode<E> treeNode = queue.poll();
            levelSize--;
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
            // 当 levelSize == 0 时，就代表着我们遍历完了一层
            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    /* 获取节点的高度：递归 ---> 左子树和右子树的最大高度 + 1
    public int height(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }*/

    /**
     * 判断是否是完全二叉树
     *
     * @return true | false
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            TreeNode<E> treeNode = queue.poll();
            if (isLeaf && !treeNode.isLeaf()) {
                return false;
            }
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            } else if (treeNode.right != null) {
                return false;
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            } else {
                // right == null
                isLeaf = true;
            }
        }
        return true;
    }

    protected TreeNode<E> search(E element) {
        checkElement(element);
        try {
            TreeNode<E> result = root;
            while (result != null) {
                int compare = compare(element, result.element);
                if (compare == 0) {
                    return result;
                } else if (compare < 0) {
                    result = result.left;
                } else {
                    result = result.right;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean contains(E element) {
        TreeNode<E> node = root;
        try {
            while (node != null) {
                int compareResult = compare(node.element, element);
                if (compareResult > 0) {
                    node = node.left;
                } else if (compareResult < 0) {
                    node = node.right;
                } else {
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((TreeNode<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((TreeNode<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node.toString();
    }

    public void setComparator(Comparator<? super E> comparator) {
        this.comparator = comparator;
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

    /**
     * 获取指定节点的前驱结点
     *
     * @param node node
     * @return 该节点的前驱结点
     */
    protected TreeNode<E> predecessor(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        TreeNode<E> result = node.left;
        // 前驱结点在左子树中
        if (result != null) {
            while (result.right != null) {
                result = result.right;
            }

            return result;
        }
        // 前驱结点在父节点、祖父节点中中
        result = node;
        while (result.parent != null && result == result.parent.left) {
            result = result.parent;
        }

        // result.parent == null 没有前驱结点
        // result == result.parent.right 找到了前驱结点
        return result.parent;
    }

    /**
     * 获取指定节点的后继结点
     *
     * @param node node
     * @return 该节点的后继结点
     */
    protected TreeNode<E> successor(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        TreeNode<E> result = node.right;
        // 前驱结点在左子树中
        if (result != null) {
            while (result.left != null) {
                result = result.left;
            }

            return result;
        }
        // 前驱结点在父节点、祖父节点中中
        result = node;
        while (result.parent != null && result == result.parent.right) {
            result = result.parent;
        }

        // result.parent == null 没有前驱结点
        // result == result.parent.right 找到了前驱结点
        return result.parent;
    }

    /**
     * 插入到树中的元素一定是非空的
     */
    protected void checkElement(E element) {
        if (element == null) {
            throw new IllegalArgumentException("插入树中的元素不能为空");
        }
    }
}
