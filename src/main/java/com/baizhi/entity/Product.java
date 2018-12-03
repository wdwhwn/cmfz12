package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by wdwhwn on 2018/10/31.
 */


public class Product{
    private String id;
    private String price;
    private String desc;
    private String url;
    private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private String location;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Product() {
    }

    public Product(String id, String price, String desc, String url, String status, Date date, String location) {
        this.id = id;
        this.price = price;
        this.desc = desc;
        this.url = url;
        this.status = status;
        this.date = date;
        this.location = location;
    }
}
