package com.mxgraph.server.biz.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mxgraph.server.biz.base.DrawBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name="t_data_user",uniqueConstraints=@UniqueConstraint(columnNames={"uid"}))
@TableName("t_data_user")
@ApiModel(value = "用户")
public class DrawUser extends DrawBaseEntity {

    private String uid;

    private String pass;

    private String token;

    private String user;

    private String name;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
