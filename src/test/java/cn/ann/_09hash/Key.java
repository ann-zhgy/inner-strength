package cn.ann._09hash;

/**
 * Description：测试对象：key
 * <p>
 * Date: 2020-11-4 16:06
 *
 * @author 88475
 * @version v1.0
 */
public class Key {
    private final int value;

    public Key(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        Key key = (Key) o;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return value / 20;
    }

    @Override
    public String toString() {
        return "Key: [" + value + "]";
    }
}
