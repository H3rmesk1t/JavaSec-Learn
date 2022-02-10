package org.h3rmesk1t.JEP290.bypass8u231;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/4 2:13 上午
 */
public class RMIRegistry {

    public static void main(String[] args) throws RemoteException {

        LocateRegistry.createRegistry(6666);
        System.out.println("RMI Registry Start...");

        while (true);
    }
}