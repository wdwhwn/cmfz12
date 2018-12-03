package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class Artical implements Serializable {
    private Integer id;

    private String title;

    private String insertImg;

    private String content;

    private Date publishDate;

    private Integer guruid;

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

    public String getInsertImg() {
        return insertImg;
    }

    public void setInsertImg(String insertImg) {
        this.insertImg = insertImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getGuruid() {
        return guruid;
    }

    public void setGuruid(Integer guruid) {
        this.guruid = guruid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", insertImg=").append(insertImg);
        sb.append(", content=").append(content);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", guruid=").append(guruid);
        sb.append("]");
        return sb.toString();
    }
}