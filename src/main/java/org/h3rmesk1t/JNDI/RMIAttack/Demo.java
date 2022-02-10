package org.h3rmesk1t.JNDI.RMIAttack;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/5 9:44 上午
 */
public interface Demo extends Remote {

    public String Demo(String name) throws RemoteException;
}