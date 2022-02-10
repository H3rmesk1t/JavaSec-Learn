package org.h3rmesk1t.JNDI.RMIAttack;

import org.h3rmesk1t.JNDI.demo.Demo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/4 11:12 上午
 */
public class DemoImpl extends UnicastRemoteObject implements Demo {

    protected DemoImpl() throws RemoteException {
        super();
    }

    public String Demo(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
