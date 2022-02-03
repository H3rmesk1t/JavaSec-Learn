package org.h3rmesk1t.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/1/28 1:11 下午
 */
public class RMIServer {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, InterruptedException {

        // 注册端口
        LocateRegistry.createRegistry(7777);

        // 创建远程对象
        RemoteObject remoteObject = new RemoteObject();

        // 绑定远程对象
        Naming.bind("rmi://localhost:7777/demoCaseBegin", remoteObject);

        // 挂起主线程避免应用退出
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }
}