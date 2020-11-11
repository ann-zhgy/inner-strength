package cn.ann._09hash;

import cn.ann.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description：
 * <p>
 * Date: 2020-11-4 16:04
 *
 * @author 88475
 * @version v1.0
 */
@DisplayName("HashMap 测试")
class HashMapTest {
    private static HashMap<Integer, Person> map;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, new Person(String.format("test%02d", i + 1), i + 20));
        }
    }

    @Test
    @DisplayName("put() 测试")
    void putTest() {
        map.put(20, new Person("John", 23));
        Assertions.assertEquals(21, map.size());
        Assertions.assertEquals(new Person("John", 23), map.get(20));
        Assertions.assertTrue(map.containsValue(new Person("John", 23)));
        Assertions.assertTrue(map.containsKey(20));
        Assertions.assertEquals(new Person("test11", 30), map.put(10, new Person("Tom", 85)));
        Assertions.assertEquals(21, map.size());
        Assertions.assertEquals(new Person("Tom", 85), map.get(10));
    }

    @Test
    @DisplayName("remove(key) 测试")
    void removeTest() {
        Person person = map.remove(10);
        Assertions.assertEquals(19, map.size());
        Assertions.assertEquals(new Person("test11", 30), person);
        Assertions.assertNull(map.remove(50));
        Assertions.assertEquals(19, map.size());
    }

    @Test
    @DisplayName("clear() isEmpty() 测试")
    void otherTest() {
        Assertions.assertFalse(map.isEmpty());
        map.clear();
        Assertions.assertEquals(0, map.size());
        Assertions.assertTrue(map.isEmpty());
    }

    @Test
    @DisplayName("桶数组自动扩容 test")
    void autoExpansionTest() {
        Assertions.assertEquals(32, Arrays.asList(map.getTable()).size());
        for (int i = 20; i < 40; i++) {
            map.put(i, new Person(String.format("test%02d", i + 1), i + 20));
        }
        Assertions.assertEquals(64, Arrays.asList(map.getTable()).size());
    }

}