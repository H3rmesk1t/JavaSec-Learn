package URLClassLoaderDemo;

import java.io.*;
/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/29 4:25 下午
 */
public class Evil {
    public Evil() throws IOException {
        Runtime.getRuntime().exec("open -a /System/Applications/Calculator.app");
    }
}
