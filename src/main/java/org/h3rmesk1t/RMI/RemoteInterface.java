package org.h3rmesk1t.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/1/28 1:17 下午
 */
public interface RemoteInterface extends Remote {

    public String demoCaseBegin() throws RemoteException;

    public String demoCaseBegin(Object name) throws RemoteException;

    public String demoCaseOver() throws RemoteException;

    public Object openCalculatorObject() throws Exception;
}