package com.mxgraph.server.biz.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mxgraph.server.biz.base.DrawBaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@Table(name="t_draw_data",uniqueConstraints=@UniqueConstraint(columnNames={"uuid"}))
@TableName("t_draw_data")
@ApiModel(value = "绘图")
public class DrawData extends DrawBaseEntity {

    @Column(unique=true, length = 64)
    private String uuid;

    private String ownerId;

    private String type;

    private String name;

    //@Lob 通常与@Basic同时使用，提高访问速度
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name=" body", columnDefinition="longblob", nullable=true)
    private byte[] body;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name=" svgData", columnDefinition="longblob", nullable=true)
    private byte[] svgData;


    private String attachment;

    private String relateCode;

    private String attribute;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getRelateCode() {
        return relateCode;
    }

    public void setRelateCode(String relateCode) {
        this.relateCode = relateCode;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public byte[] getSvgData() {
        return svgData;
    }

    public void setSvgData(byte[] svgData) {
        this.svgData = svgData;
    }
}
