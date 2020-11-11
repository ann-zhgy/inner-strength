package cn.ann._03linked.list;

import cn.ann.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Description：自定义单向循环链表测试
 * <p>
 * Date: 2020-10-29 12:46
 *
 * @author 88475
 * @version v1.0
 */
@DisplayName("SingleCircleLinkedList 测试")
class SingleCircleLinkedListTest {
    private List<Person> persons;

    @BeforeEach
    void setUp() {
        persons = new SingleCircleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            persons.add(new Person(String.format("test%02d", i + 1), i + 20));
        }
    }

    @Test
    @DisplayName("add() 测试")
    void addTest() {
        persons.add(new Person("John", 23));
        Assertions.assertEquals(21, persons.size());
        Assertions.assertEquals("test04", persons.get(3).getName());
        Assertions.assertEquals(new Person("John", 23), persons.get(20));
    }

    @Test
    @DisplayName("add(index, element) 测试")
    void add2Test() {
        persons.add(10, new Person("John", 23));
        Assertions.assertEquals(21, persons.size());
        Assertions.assertEquals("test04", persons.get(3).getName());
        Assertions.assertEquals("test15", persons.get(15).getName());
        Assertions.assertEquals(new Person("John", 23), persons.get(10));
    }

    @Test
    @DisplayName("remove(index) 测试")
    void removeTest() {
        Person person = persons.remove(5);
        Assertions.assertEquals("test06", person.getName());
        Assertions.assertEquals(19, persons.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> persons.remove(200));
    }

    @ParameterizedTest
    @DisplayName("remove(element) 测试")
    @CsvSource({
            "test07, 26",
            "test07, 25",
    })
    void remove2Test(String name, int age) {
        boolean removed = persons.remove(new Person(name, age));
        if (removed) {
            Assertions.assertEquals(19, persons.size());
        } else {
            Assertions.assertEquals(20, persons.size());
        }
    }

    @Test
    @DisplayName("clear() 测试")
    void clearTest() {
        persons.clear();
        Assertions.assertEquals(0, persons.size());
    }

    @ParameterizedTest
    @DisplayName("indexOf(element) 测试")
    @CsvSource({
            "true, test07, 26",
            "false, test07, 27",
    })
    void containsTest(boolean contains, String name, int age) {
        Assertions.assertEquals(contains, persons.contains(new Person(name, age)));
    }

    @ParameterizedTest
    @DisplayName("indexOf(element) 测试")
    @CsvSource({
            "6, test07, 26",
            "-1, test07, 27",
    })
    void indexOfTest(int index, String name, int age) {
        Assertions.assertEquals(index, persons.indexOf(new Person(name, age)));
    }

    @Test
    @DisplayName("set(index, element) 测试")
    void setTest() {
        Person person = new Person("John", 17);
        Person oldPerson = persons.set(10, person);
        Assertions.assertEquals(new Person("test11", 30), oldPerson);
        Assertions.assertEquals(person, persons.get(10));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> persons.set(50, person));
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