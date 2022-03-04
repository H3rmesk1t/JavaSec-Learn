package org.h3rmesk1t.XMLDecode;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/3 6:29 下午
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class DemoHandler extends DefaultHandler {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            DemoHandler dh = new DemoHandler();
            String path = "src/main/java/org/h3rmesk1t/XMLDecode/POC1.xml";
            File file = new File(path);
            parser.parse(file, dh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters()");
        super.characters(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("startDocument()");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument()");
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("startElement()");
        for (int i = 0; i < attributes.getLength(); i++) {
            // getQName()是获取属性名称
            System.out.print(attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"\n");
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("endElement()");
        System.out.println(uri + localName + qName);
        super.endElement(uri, localName, qName);
    }
}
