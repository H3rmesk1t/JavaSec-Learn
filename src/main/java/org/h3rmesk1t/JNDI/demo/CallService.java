package org.h3rmesk1t.JNDI.demo;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/4 11:13 上午
 */
public class CallService {

    public static void main(String[] args) throws Exception {

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        Context context = new InitialContext(env);

        Registry registry = LocateRegistry.createRegistry(1099);
        Demo demo = new DemoImpl();
        registry.bind("demo", demo);

        Demo rDemo = (Demo) context.lookup("demo");
        System.out.println(rDemo.Demo("h3rmesk1t"));
    }
}