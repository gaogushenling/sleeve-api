package com.robinstudio.sleeveapi.model;

// model 不一定对应一张数据库表，也可以是字段值存储对应的结构

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecDO {
    private Long keyId;
    private String key;
    private Long valueId;
    private String value;
}
