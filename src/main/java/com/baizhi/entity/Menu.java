package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private Integer id;

    private String title;

    private Integer parentId;

    private String iconCls;

    private String url;

     private List<Menu> list;

    public Menu(Integer id) {
        this.id = id;
    }

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }

    public Menu(Integer id, String title, Integer parentId, String iconCls, String url, List<Menu> list) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.iconCls = iconCls;
        this.url = url;
        this.list = list;
    }

    public Menu() {
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", parentId=").append(parentId);
        sb.append(", iconCls=").append(iconCls);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }
}