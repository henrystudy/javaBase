package com.javabase.server.basic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * SAX解析练习
 *
 * @author: Henry Zheng
 * @date: Created in $[TIME] $[DATE]
 * @modified by:
 */

public class XmlTest01 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //SAX解析
        //1、获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        //3、编写处理器
        //4、加载文档 Document 注册处理器
        PersonHandler handler = new PersonHandler();
        //5、解析
        parser.parse(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/javabase/server/basic/person.xml")
                , handler);

        //获取xml数据
        List<Person> persons = handler.getPersons();
        for (Person p:persons){
            System.out.println(p.getName() + "-->" + p.getAge());
        }
    }
}

class PersonHandler extends DefaultHandler {
    private Person person;
    private List<Person> persons;
    private String tag;//存储操作标签

    @Override
    public void startDocument() {
        System.out.println("----解析文档开始----");
        persons = new ArrayList<Person>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + "-->解析开始");
        if (qName != null) {
            tag = qName;//存储标签名
            if(tag.equals("person")){
                person = new Person();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch, start, length).trim();//去掉空格
        if(tag != null){//处理了空值
            if(tag.equals("name")){
                person.setName(contents);
//                if(contents.length() > 0){
//                }
            }else if(tag.equals("age")){
                person.setAge(Integer.valueOf(contents));
//                if(contents.length() > 0){
//                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "-->解析结束");

        if(qName != null){
            if(qName.equals("person")){
                persons.add(person);
            }
        }
        tag = null;//丢弃tag
    }

    @Override
    public void endDocument() {
        System.out.println("----解析文档结束----");
    }

    public List<Person> getPersons(){
        return persons;
    }
}
