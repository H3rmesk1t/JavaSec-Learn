package org.h3rmesk1t.XMLDecode;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/3 5:12 下午
 */
public class EncodeDemo {

    public static void main(String[] args) throws Exception {

        XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("src/main/java/org/h3rmesk1t/XMLDecode/POC1.xml")));
        String name = "h3rmesk1t";
        xmlEncoder.writeObject(name);
        xmlEncoder.close();
    }
}