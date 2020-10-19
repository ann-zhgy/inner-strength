package cn.ann.part01._06binary.tree;

/**
 * Description：遍历方式
 * <p>
 * Date: 2020-9-19 12:35
 *
 * @author 88475
 * @version v1.0
 */
public enum TravarsalOrder {
    PREORDER("preorder"), // 前序遍历
    INORDER("inorder"), // 中序遍历
    POSTORDER("postorder"), // 后序遍历
    LEVELORDER("level"); // 层级遍历
    private final String order;

    TravarsalOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
