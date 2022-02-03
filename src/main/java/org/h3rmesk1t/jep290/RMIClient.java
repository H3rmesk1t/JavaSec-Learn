package org.h3rmesk1t.jep290;

import org.h3rmesk1t.rmi.RemoteInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/1/30 7:53 下午
 */
public class RMIClient {
    public static void main(String[] args) throws Exception {

        // 获取远程对象实例
        // RemoteInterface stub = (RemoteInterface) Naming.lookup("//localhost:4444/demoCaseBegin");

        // 获取远程对象实例
        Registry registry = LocateRegistry.getRegistry("localhost", 7777);
        org.h3rmesk1t.rmi.RemoteInterface stub = (RemoteInterface) registry.lookup("demo");

        // 方法调用
        System.out.println("方法调用结果: " + stub.demoCaseBegin(stub.openCalculatorObject()));
    }
}
