package org.h3rmesk1t.Hessian;

import java.io.Serializable;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/11 11:39 上午
 */
public class Demo implements Serializable {

    private int age;
    private String name;


    public int getAge() {
        System.out.println("getAge call");
        return age;
    }

    public void setAge(int age) {
        System.out.println("setAge call");
        this.age = age;
    }

    public String getName() {
        System.out.println("getName call");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName call");
        this.name = name;
    }

    public Demo() {
        System.out.println("Demo default constractor call");
    }


    public Demo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "My name is " + name + " and my age is " + age;
    }
}
