package org.h3rmesk1t.Fastjson.demo;

import com.alibaba.fastjson.JSON;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/7 8:25 下午
 */
public class UnSerialDemo {

    public static void main(String[] args) {

        String jsonStringWithoutType = "{\"name\":\"h3rmesk1t\"}";
        String jsonStringWithType = "{\"@type\":\"org.h3rmesk1t.fastjson.demo.Demo\",\"name\":\"h3rmesk1t\"}";

        // JSON.parse without type
        System.out.println("parse...");
        System.out.println(JSON.parse(jsonStringWithoutType));

        // JSON.parseObject without type
        System.out.println("parseObject...");
        System.out.println(JSON.parseObject(jsonStringWithoutType));

        // JSON.parseObject(text, Class) without type
        System.out.println("parseObject(text, Class)...");
        System.out.println(JSON.parseObject(jsonStringWithType, Demo.class));

        // JSON.parse with type
        System.out.println("parse...");
        System.out.println(JSON.parse(jsonStringWithType));

        // JSON.parseObject with type
        System.out.println("parseObject...");
        System.out.println(JSON.parseObject(jsonStringWithType));
    }
}
