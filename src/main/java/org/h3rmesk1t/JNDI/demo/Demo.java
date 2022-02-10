package org.h3rmesk1t.JNDI.demo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/4 11:12 上午
 */
public interface Demo extends Remote {

    public String Demo(String name) throws RemoteException;
}
