package cn.ann._09hash;

import cn.ann._06binary.tree.util.BinaryTreeInfo;
import cn.ann._06binary.tree.util.BinaryTrees;
import cn.ann._08map.Map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * Description：哈希表
 * <p>
 * Date: 2020-10-30 17:31
 *
 * @author 88475
 * @version v1.0
 */
@SuppressWarnings("unchecked")
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private Node<K, V>[] table;

    public HashMap() {
        this(0);
    }

    public HashMap(int capacity) {
        if (capacity <= 0) {
            table = new Node[DEFAULT_CAPACITY];
        } else {
            table = new Node[capacity];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        size = 0;
        Arrays.fill(table, null);
    }

    @Override
    public V put(K key, V value) {
        resize();

        int hashCode = hash(key);
        int index = index(hashCode);
        Node<K, V> root = table[index];

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size++;

            // 新添加节点之后的处理
            afterPut(root);
            return null;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<K, V> parent = null;
        Node<K, V> node = root;
        Node<K, V> result;
        // 记录是否扫描过
        boolean searched = false;
        int cmp = 0;
        while (node != null) {
            parent = node;
            if (hashCode > node.hash) {
                cmp = 1;
            } else if (hashCode < node.hash) {
                cmp = -1;
            } else if (Objects.equals(key, node.key)) {
                cmp = 0;
            } else if (searched) { // 扫描过，没找到
                cmp = System.identityHashCode(key) - System.identityHashCode(node.key);
            } else {
                // 递归寻找key
                if ((node.left != null && (result = node(node.left, key)) != null)
                        || (node.right != null && (result = node(node.right, key)) != null)) {
                    // 已经存在这个key
                    node = result;
                    cmp = 0;
                } else { // 不存在这个key
                    searched = true;
                    cmp = System.identityHashCode(key) - System.identityHashCode(node.key);
                }
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) {
            return false;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (Node<K, V> node : table) {
            if (node == null) {
                continue;
            }

            queue.offer(node);
            while (!queue.isEmpty()) {
                Node<K, V> poll = queue.poll();
                if (Objects.equals(value, poll.value)) {
                    return true;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }

        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) {
            return;
        }

        Stack<Node<K, V>> stack = new Stack<>();
        for (Node<K, V> node : table) {
            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    if (!visitor.visit(node.key, node.value)) {
                        return;
                    }
                    stack.push(node);
                    node = node.left;
                }
                if (!stack.isEmpty()) {
                    node = stack.pop();
                    node = node.right;
                }
            }
        }
    }

    public void print() {
        System.out.println("-------------------");
        for (Node<K, V> node : table) {
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return node;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>)node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>)node).right;
                }

                @Override
                public Object string(Object node) {
                    return node.toString();
                }
            });
            System.out.println("-------------------");
        }
    }

    public Node<K, V>[] getTable() {
        return table;
    }

    private V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        size--;

        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.value = s.value;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index(node.hash)] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index(node.hash)] = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }

        return oldValue;
    }

    private void resize() {
        float loadFactor = (float) (1.0 * size / table.length);
        if (loadFactor < DEFAULT_LOAD_FACTOR) {
            return;
        }
        int newLength;
        if (table.length < DEFAULT_CAPACITY) {
            newLength = DEFAULT_CAPACITY;
        } else {
            newLength = table.length << 1;
        }
        Node<K, V>[] oldTable = table;
        table = new Node[newLength];
        size = 0;

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (Node<K, V> node : oldTable) {
            if (node == null) {
                continue;
            }
            queue.offer(node);
            while (!queue.isEmpty()) {
                Node<K, V> poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }

                put(poll.key, poll.value);
            }
        }

    }

    private Node<K, V> node(K key) {
        Node<K, V> root = table[index(hash(key))];
        return node(root, key);
    }

    private Node<K, V> node(Node<K, V> node, K key) {
        int keyHash = hash(key);
        Node<K, V> result;
        while (node != null) {
            if (keyHash > node.hash) {
                node = node.right;
            } else if (keyHash < node.hash) {
                node = node.left;
            } else if (Objects.equals(node.key, key)) {
                return node;
            } else {
                if (node.right != null && (result = node(node.right, key)) != null) {
                    return result;
                } else {
                    node = node.left;
                }
            }
        }

        return null;
    }

    private void afterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
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

    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        // 前驱节点在左子树当中（left.right.right.right....）
        Node<K, V> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 根据哈希计算索引
     *
     * @param hash 哈希值
     * @return 索引
     */
    private int index(int hash) {
        return (hash ^ (hash >>> 16)) & (table.length - 1);
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) {
            return;
        }

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand.hash)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return null;
        }
        node.color = color;
        return node;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        boolean color = RED;
        Node<K, V> left, right, parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public final String toString() {
            return key + " = " + value;
        }
    }
}
