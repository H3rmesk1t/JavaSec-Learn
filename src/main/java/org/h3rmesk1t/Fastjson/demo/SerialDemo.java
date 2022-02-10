package org.h3rmesk1t.Fastjson.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/7 8:12 下午
 */
public class SerialDemo {

    public static void main(String[] args) {

        Demo demo = new Demo();
        demo.setName("h3rmesk1t");

        String jsonString = JSON.toJSONString(demo, SerializerFeature.WriteClassName);
        System.out.println("SerialResult with SerializerFeature.WriteClassName...");
        System.out.println(jsonString);

        String jsonStringWithoutSerializerFeatureWriteClassName = JSON.toJSONString(demo);
        System.out.println("SerialResult without SerializerFeature.WriteClassName...");
        System.out.println(jsonStringWithoutSerializerFeatureWriteClassName);
    }
}