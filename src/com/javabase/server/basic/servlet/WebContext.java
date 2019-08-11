package com.javabase.server.basic.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 处理web.xml数据成map方便直接根据pattern查找对应的clz
 * @author: Henry Zheng
 * @date: Created in 14:40 2019/8/4
 * @modified by:
 */

public class WebContext {
    private List<Mapping> mappings = new ArrayList<Mapping>();
    private List<Entity> entities = new ArrayList<Entity>();
    //定义两个map保存对应数据
    private Map<String,String> patternMap= new HashMap<String,String>();
    private Map<String,String> entityMap= new HashMap<String,String>();

    public WebContext(List<Mapping> mappings, List<Entity> entities) {
        this.mappings = mappings;
        this.entities = entities;

        for(Mapping m:mappings){
            for(String s:m.getPatterns()) {
                patternMap.put(s, m.getServeletName());
            }
        }

        for(Entity e:entities){
            entityMap.put(e.getservletName(),e.getClzName());
        }
    }

    public String getClz(String patternUrl){
        String sName = patternMap.get(patternUrl);
        return entityMap.get(sName);
    }
}
