package com.javabase.server.basic.servlet;

/**
 * @description: 根据serveletName获取实体类
 * @author: Henry Zheng
 * @date: Created in 13:03 2019/8/4
 * @modified by:
 */

public class Entity {
    private String servletName;
    private String clzName;

    public Entity() {
    }

    public String getservletName() {
        return servletName;
    }

    public void setServletName(String serveletName) {
        this.servletName = serveletName;
    }

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }
}
