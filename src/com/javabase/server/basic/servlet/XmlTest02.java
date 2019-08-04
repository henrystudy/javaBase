package com.javabase.server;

import com.javabase.server.basic.Person;
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
 * @description: 解析web.xml
 * @author: Henry Zheng
 * @date: Created in 12:48 2019/8/4
 * @modified by:
 */

public class XmlTest02 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //SAX解析
        //1、获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        //3、编写处理器
        //4、加载文档 Document 注册处理器
        WebHandler handler = new WebHandler();
        //5、解析
        parser.parse(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/javabase/server/web.xml")
                , handler);

        //获取xml数据
        List<Mapping> mappings = handler.getMappings();
        List<Entity> entities = handler.getEntities();

        System.out.println(mappings.size());
        for (Mapping m:mappings){
            System.out.println(m.getPatterns() + "-->" + m.getServeletName());
        }

        System.out.println(entities.size());
        for (Entity e:entities){
            System.out.println(e.getservletName() + "-->" + e.getClzName());
        }
    }
}

class WebHandler extends DefaultHandler {
    private List<Mapping> mappings;
    private List<Entity> entities;
    private Mapping mapping;
    private Entity entity;
    //存储操作标签
    private String tag;
    //标识什么节点
    private boolean isMapping = false;

    @Override
    public void startDocument() {
        System.out.println("----解析文档开始----");
        mappings = new ArrayList<Mapping>();
        entities = new ArrayList<Entity>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + "-->解析开始");
        if (qName != null) {
            //存储标签名
            tag = qName;
            if (tag.equals("servlet-mapping")) {
                mapping = new Mapping();
                isMapping = true;
            }else if (tag.equals("servlet")) {
                entity = new Entity();
                isMapping = false;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //trim()去掉空格
        String contents = new String(ch, start, length).trim();
        //处理了空值
        if (tag != null) {
            if (isMapping){
                if (tag.equals("servlet-name")) {
                    mapping.setServletName(contents);
                } else if (tag.equals("url-pattern")) {
                    //这里使用定义的容器添加方法
                    mapping.addPatterns(contents);
                }
            } else{
                if (tag.equals("servlet-name")) {
                    entity.setServletName(contents);
                } else if (tag.equals("servlet-class")) {
                    //这里使用定义的容器添加方法
                    entity.setClzName(contents);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "-->解析结束");

        if (qName != null) {
            if (qName.equals("servlet-mapping")) {
                mappings.add(mapping);
            }else if (qName.equals("servlet")) {
                entities.add(entity);
            }
        }
        //丢弃tag
        tag = null;
    }

    @Override
    public void endDocument() {
        System.out.println("----解析文档结束----");
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}