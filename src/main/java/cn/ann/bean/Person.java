package cn.ann.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Description：测试用的bean
 * <p>
 * Date: 2020-9-2 13:52
 *
 * @author 88475
 * @version v1.0
 */
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
}