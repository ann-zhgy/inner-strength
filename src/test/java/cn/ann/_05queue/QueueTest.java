package cn.ann._05queue;

import cn.ann.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Description：自定义队列测试
 * <p>
 * Date: 2020-10-29 13:19
 *
 * @author 88475
 * @version v1.0
 */
@DisplayName("Queue 测试")
class QueueTest {
    private static Queue<Person> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
        for (int i = 0; i < 20; i++) {
            queue.enQueue(new Person(String.format("test%02d", i + 1), i + 20));
        }
    }

    @Test
    @DisplayName("enQueue() 测试")
    void enQueueTest() {
        queue.enQueue(new Person("John", 23));
        Assertions.assertEquals(21, queue.size());
        Assertions.assertTrue(queue.contains(new Person("John", 23)));
    }

    @Test
    @DisplayName("deQueue() 测试")
    void deQueueTest() {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Assertions.assertEquals(new Person(String.format("test%02d", i + 1), i + 20), queue.deQueue());
        }
        Assertions.assertThrows(EmptyQueueException.class, queue::deQueue);
    }

    @Test
    @DisplayName("front() 测试")
    void frontTest() {
        for (int i = 0; i < queue.size(); i++) {
            Person person = queue.front();
            Assertions.assertEquals(queue.deQueue(), person);
        }
    }

    @Test
    @DisplayName("clear() isEmpty() 测试")
    void otherTest() {
        Assertions.assertFalse(queue.isEmpty());
        queue.clear();
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(0, queue.size());
    }
}