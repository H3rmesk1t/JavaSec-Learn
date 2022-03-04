package org.h3rmesk1t.XMLDecode;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/3 5:18 下午
 */
public class DecodeDemo {

    public static void main(String[] args) throws Exception {

        XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("src/main/java/org/h3rmesk1t/XMLDecode/POC1.xml")));
        Object object = xmlDecoder.readObject();
        System.out.println(object);
        xmlDecoder.close();
    }
}