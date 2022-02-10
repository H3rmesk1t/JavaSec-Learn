package org.h3rmesk1t.JNDI.RMIAttack;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/5 11:12 上午
 */
public class JNDIClient {

    public static void main(String[] args) throws NamingException {

        String url = "rmi://127.0.0.1:1099/reference";
        Context ctx = new InitialContext();
        ctx.lookup(url);
    }
}
