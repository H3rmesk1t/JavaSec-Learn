package org.h3rmesk1t.jep290.bypass8u221;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/4 2:13 上午
 */
public class RMIRegistry {

    public static void main(String[] args) throws RemoteException {

        LocateRegistry.createRegistry(2222);
        System.out.println("RMI Registry Start...");

        while (true);
    }
}
