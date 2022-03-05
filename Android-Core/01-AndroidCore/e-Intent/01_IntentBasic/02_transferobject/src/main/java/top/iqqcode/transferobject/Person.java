package top.iqqcode.transferobject;

import java.io.Serializable;

/**
 * @Author: iqqcode
 * @Date: 2021-03-07 10:12
 * @Description:
 */
public class Person implements Serializable {
    private String name;
    private int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
