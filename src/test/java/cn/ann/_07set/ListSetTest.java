package cn.ann._07set;

import cn.ann.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Description：自定义 list 实现的集合测试
 * <p>
 * Date: 2020-10-30 11:33
 *
 * @author 88475
 * @version v1.0
 */
@DisplayName("ListSet 测试")
class ListSetTest {
    private Set<Person> persons;

    @BeforeEach
    void setUp() {
        persons = new ListSet<>();
        for (int i = 0; i < 20; i++) {
            persons.add(new Person(String.format("test%02d", i + 1), i + 20));
        }
    }

    @Test
    @DisplayName("add() 测试")
    void addTest() {
        persons.add(new Person("John", 23));
        Assertions.assertEquals(21, persons.size());
        persons.add(new Person("John", 23));
        Assertions.assertEquals(21, persons.size());
    }

    @ParameterizedTest
    @DisplayName("remove(element) 测试")
    @CsvSource({
            "test07, 26",
            "test07, 25",
    })
    void removeTest(String name, int age) {
        boolean removed = persons.remove(new Person(name, age));
        if (removed) {
            Assertions.assertEquals(19, persons.size());
        } else {
            Assertions.assertEquals(20, persons.size());
        }
    }

    @ParameterizedTest
    @DisplayName("contains(element) 测试")
    @CsvSource({
            "true, test07, 26",
            "false, test07, 27",
    })
    void containsTest(boolean contains, String name, int age) {
        Assertions.assertEquals(contains, persons.contains(new Person(name, age)));
    }

    @Test
    @DisplayName("clear(), isEmpty() 测试")
    void otherTest() {
        Assertions.assertFalse(persons.isEmpty());
        persons.clear();
        Assertions.assertEquals(0, persons.size());
        Assertions.assertTrue(persons.isEmpty());
    }
}