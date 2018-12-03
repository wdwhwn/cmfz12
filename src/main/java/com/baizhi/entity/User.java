package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;

    private String headPic;

    private String dharmaName;

    private String name;

    private Integer sex;

    private String location;

    private String province;

    private String city;

    private String sign;

    private String phonenum;

    private String password;

    private String salt;

    private String status;

    private Date regdate;

    private Integer guruId;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", headPic='" + headPic + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", location='" + location + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", regdate=" + regdate +
                ", guruId=" + guruId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public User() {
    }

    public User(Integer id, String headPic, String dharmaName, String name, Integer sex, String location, String province, String city, String sign, String phonenum, String password, String salt, String status, Date regdate, Integer guruId) {
        this.id = id;
        this.headPic = headPic;
        this.dharmaName = dharmaName;
        this.name = name;
        this.sex = sex;
        this.location = location;
        this.province = province;
        this.city = city;
        this.sign = sign;
        this.phonenum = phonenum;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.regdate = regdate;
        this.guruId = guruId;
    }
}