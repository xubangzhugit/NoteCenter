package javaNote.xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;

public class Xml {

     public static void main(String[] args){
         try{
             //DOM解析
             InputStream resourceAsStream = Xml.class.getResourceAsStream("book.xml");
             DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
             Document parse = documentBuilderFactory.newDocumentBuilder().parse(resourceAsStream);
             NodeList document = parse.getElementsByTagName("book");
             System.out.println(document);
             //sax解析
             SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
             SAXParser saxParser = saxParserFactory.newSAXParser();
             saxParser.parse(resourceAsStream,new MyHandle());
//             JacksonTest.test();
         }catch(Exception e){
             e.printStackTrace();
         }finally{

         }
     }
}
class MyHandle extends DefaultHandler{

    /**
     * 开始解析document
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {

    }

    /**
     * 结束解析document
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
    }

    /**
     * 开始解析element
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    }

    /**
     * 结束解析element
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    /**
     * 解析字符
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch, start, length));
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }
}
class JacksonTest {
    public static void main(String[] args){
//        try{
//            InputStream resourceAsStream = JacksonTest.class.getResourceAsStream("book.xml");
//            JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
//            XmlMapper xmlMapper = new XmlMapper(jacksonXmlModule);
//            Book book = xmlMapper.readValue((XMLStreamReader) resourceAsStream, Book.class);
//            System.out.println(book);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//
//        }
    }
    @Data
    class Book{
        private String name;
        private String author;
        private Integer isbn;
        private List<Tag> tags;
        private String pubData;

        class Tag{
            private String tag;
        }
    }
}