package com.robinstudio.sleeveapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "spu_img", schema = "sleeve-api", catalog = "")
public class SpuImgDO extends BaseEntity {
    @Id
    private int id;
    private String img;
    private Integer spuId;
}
