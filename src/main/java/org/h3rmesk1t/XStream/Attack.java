package org.h3rmesk1t.XStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/2 4:33 下午
 */
public class Attack {

    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream xmlFile = new FileInputStream("/Users/h3rmesk1t/Desktop/JavaSec-Learn/src/main/java/org/h3rmesk1t/XStream/CVE-2021-39149.xml");
        XStream xStream = new XStream(new DomDriver());
        DemoInterface demo = (DemoInterface) xStream.fromXML(xmlFile);
        demo.output();
    }
}
