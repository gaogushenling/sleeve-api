package com.robinstudio.sleeveapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
// 标记实体类的父类，使字段与数据库映射，Entity与实体类对应一张表
public abstract class BaseEntity {
    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date deleteTime;
}
