package org.h3rmesk1t.Hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/11 11:38 上午
 */
public class HJessianSerialization {

    public static <T> byte[] hserialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(t);
            data = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T hdeserialize(byte[] date) {
        if (date == null) {
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(date);
            HessianInput hessianInput = new HessianInput(byteArrayInputStream);
            obj = hessianInput.readObject();
            hessianInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    public static <T> byte[] jdkserialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
            data = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T jdkdeserialize(byte[] date) {
        if (date == null) {
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(date);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo(20, "h3rmesk1t");

        long htime1 = System.currentTimeMillis();
        byte[] hdata = hserialize(demo);
        long htime2 = System.currentTimeMillis();
        System.out.println("hessian serialize result length = " + hdata.length + "," + "cost time：" + (htime2 - htime1));

        long htime3 = System.currentTimeMillis();
        Demo hdemo = hdeserialize(hdata);
        long htime4 = System.currentTimeMillis();
        System.out.println("hessian deserialize result: " + hdemo + "," + "cost time：" + (htime4 - htime3) + "\n");

        long jdktime1 = System.currentTimeMillis();
        byte[] jdkdata = hserialize(demo);
        long jdktime2 = System.currentTimeMillis();
        System.out.println("jdk serialize result length = " + jdkdata.length + "," + "cost time：" + (jdktime2 - jdktime1));

        long jdktime3 = System.currentTimeMillis();
        Demo jdkdemo = hdeserialize(jdkdata);
        long jdktime4 = System.currentTimeMillis();
        System.out.println("jdk deserialize result: " + jdkdemo + "," + "cost time：" + (jdktime4 - jdktime3) + "\n");

    }
}
