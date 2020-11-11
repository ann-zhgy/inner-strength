package cn.ann._06binary.tree.balance;

import cn.ann._06binary.tree.util.BinaryTrees;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Description：红黑树测试
 * <p>
 * Date: 2020-10-29 17:32
 *
 * @author 88475
 * @version v1.0
 */
@DisplayName("RedBlackTree测试")
class RedBlackTreeTest {
    private static RedBlackTree<Integer> tree;
    private static int[] numbers;

    @BeforeEach
    void setUp() {
        tree = new RedBlackTree<>();
        numbers = new int[]{2109, 1263, 663, 1026, 1628, 1315, 1990, 1922, 3206, 2620, 2568, 2614, 2836};
        for (int number : numbers) {
            tree.add(number);
        }
    }

    @Test
    @DisplayName("add(element) 测试")
    void addTest() {
        Random random = new Random();
        int num = random.nextInt(5000);
        tree.add(num);
        BinaryTrees.print(tree);
        Assertions.assertTrue(tree.contains(num));
        Assertions.assertTrue(tree.height() < 6);
        Assertions.assertEquals(14, tree.size());
    }

    @Test
    @DisplayName("remove() 测试")
    void removeTest() {
        int size = tree.size();
        for (int i = 0; i < size; i++) {
            tree.remove(numbers[i]);
            Assertions.assertEquals(size - 1 - i, tree.size());
        }
    }
}