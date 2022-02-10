package org.h3rmesk1t.Fastjson.demo;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/7 8:08 下午
 */
public class Demo {

    private String name;

    public Demo() {
        System.out.println("构造函数");
    }

    public String getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }
}
