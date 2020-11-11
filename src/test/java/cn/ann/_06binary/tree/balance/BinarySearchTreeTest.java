package cn.ann._06binary.tree.balance;

import cn.ann.bean.Person;
import cn.ann._06binary.tree.util.BinaryTrees;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * Description：二叉搜索树测试
 * <p>
 * Date: 2020-10-29 14:51
 *
 * @author 88475
 * @version v1.0
 */
@DisplayName("BinarySearchTree测试")
class BinarySearchTreeTest {
    private static BinarySearchTree<Person> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>(Comparator.comparing(Person::getName).thenComparingInt(Person::getAge));
        int[] numbers = {8, 4, 2, 1, 3, 6, 5, 7, 12, 16, 10, 9, 11, 14, 13, 15, 18, 17, 19, 20};
        for (int number : numbers) {
            tree.add(new Person(String.format("test%02d", number), number + 20));
        }
    }

    @Test
    @DisplayName("add(element) 测试")
    void addTest() {
        tree.add(new Person("John", 23));
        BinaryTrees.print(tree);
        Assertions.assertEquals(21, tree.size());
        Assertions.assertTrue(tree.contains(new Person("John", 23)));
    }

    @Test
    @DisplayName("remove() 测试")
    void removeTest() {
        for (int size = tree.size(); size > 0; size--) {
            tree.remove(new Person(String.format("test%02d", size), size + 20));
            Assertions.assertEquals(size - 1, tree.size());
        }
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    @DisplayName("clear() 测试")
    void otherTest() {
        tree.clear();
        Assertions.assertTrue(tree.isEmpty());
    }
}