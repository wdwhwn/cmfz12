package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class Guru implements Serializable {
    private Integer id;

    private String headPic;

    private String dharmaName;

    private Integer status;

    private Date createDate;

    private static final long serialVersionUID = 1L;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", headPic=").append(headPic);
        sb.append(", dharmaName=").append(dharmaName);
        sb.append(", status=").append(status);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }
}