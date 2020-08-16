package com.robinstudio.sleeveapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "spu_detail_img", schema = "sleeve-api", catalog = "")
public class SpuDetailImgDO extends BaseEntity{
    @Id
    private int id;
    private String img;
    private Integer spuId;
    private int index;
}
