package cn.ann._04stack;

import cn.ann.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

/**
 * Description：自定义栈测试
 * <p>
 * Date: 2020-10-29 12:49
 *
 * @author 88475
 * @version v1.0
 */
@DisplayName("Stack 测试")
class StackTest {
    private static Stack<Person> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(new Person(String.format("test%02d", i + 1), i + 20));
        }
    }

    @Test
    @DisplayName("push() 测试")
    void pushTest() {
        stack.push(new Person("john", 23));
        Assertions.assertEquals(21, stack.size());
        Assertions.assertTrue(stack.contains(new Person("john", 23)));
        Assertions.assertEquals(new Person("john", 23), stack.pop());
    }

    @Test
    @DisplayName("pop() 测试")
    void popTest() {
        for (int i = stack.size(); i > 0; i--) {
            Assertions.assertEquals(new Person(String.format("test%02d", i), i + 19), stack.pop());
        }
        Assertions.assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    @DisplayName("top() 测试")
    void topTest() {
        Person person = stack.top();
        Assertions.assertEquals(20, stack.size());
        Assertions.assertEquals(person, stack.pop());
    }

    @Test
    @DisplayName("clear() isEmpty() 测试")
    void otherTest() {
        Assertions.assertFalse(stack.isEmpty());
        stack.clear();
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
    }
}