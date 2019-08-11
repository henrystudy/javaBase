package com.javabase.server.basic.servlet;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 保存获取url对应的ServeletName
 *
 * <servlet-mapping>
 *   <servlet-name>login</servlet-name>
 *   <url-pattern>/login</url-pattern>
 *   <url-pattern>/g</url-pattern>
 *  </servlet-mapping>
 *  <servlet-mapping>
 *   <servlet-name>reg</servlet-name>
 *   <url-pattern>/reg</url-pattern>
 *  </servlet-mapping>
 *
 * @author: Henry Zheng
 * @date: Created in 12:52 2019/8/4
 * @modified by:
 */

public class Mapping {

    private String serveltName;
    private Set<String> patterns;

    public Mapping() {
        patterns = new HashSet<>();
    }

    public Set<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<String> patterns) {
        this.patterns = patterns;
    }

    public String getServeletName() {
        return serveltName;
    }

    public void setServletName(String servletName) {
        this.serveltName = servletName;
    }

    public void addPatterns(String pattern){
        patterns.add(pattern);
    }
}
