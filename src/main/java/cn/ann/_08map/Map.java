package cn.ann._08map;

/**
 * Description：映射：key-value
 * <p>
 * Date: 2020-10-30 15:14
 *
 * @author 88475
 * @version v1.0
 */
public interface Map<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {
        boolean stop;

        public abstract boolean visit(K key, V value);
    }
}
