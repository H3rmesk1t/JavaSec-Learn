package org.h3rmesk1t.XStream;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/2 11:25 上午
 */
public class Demo implements DemoInterface {

    String name;

    public void output() {

        System.out.println("Hello, " + this.name);
    }
}