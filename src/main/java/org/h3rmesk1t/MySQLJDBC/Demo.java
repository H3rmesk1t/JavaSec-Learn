package org.h3rmesk1t.MySQLJDBC;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/23 8:55 上午
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        // mysql-connector-java 5.x
        // String jdbcUrl = "jdbc:mysql://127.0.0.1:9999/mysql?characterEncoding=utf8&useSSL=false&statementInterceptors=com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor&autoDeserialize=true";

        // mysql-connector-java 8.x
        String jdbcUrl = "jdbc:mysql://127.0.0.1:9999/mysql?characterEncoding=utf8&useSSL=false&queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&autoDeserialize=true";

        String username = "root";
        String password = "20010728";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
    }
}