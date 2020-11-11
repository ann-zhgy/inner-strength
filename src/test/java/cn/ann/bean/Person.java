package cn.ann.bean;

import lombok.Data;

import java.util.Objects;

/**
 * Description：测试用的bean
 * <p>
 * Date: 2020-9-2 13:52
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}