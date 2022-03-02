package org.h3rmesk1t.XStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/2 11:27 上午
 */
public class DemoXML {

    public static void main(String[] args) throws FileNotFoundException {

//        Demo demo = new Demo();
//        demo.name = "h3rmesk1t";
//        XStream xStream = new XStream(new DomDriver());
//        String xml = xStream.toXML(demo);
//        System.out.println(xml);
        FileInputStream xml = new FileInputStream("/Users/h3rmesk1t/Desktop/JavaSec-Learn/src/main/java/org/h3rmesk1t/XStream/demo.xml");
        XStream xStream = new XStream(new DomDriver());
        Demo demo = (Demo) xStream.fromXML(xml);
        demo.output();
    }
}