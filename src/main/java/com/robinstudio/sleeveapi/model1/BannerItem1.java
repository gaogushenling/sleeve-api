package com.robinstudio.sleeveapi.model1;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bannerItem")
public class BannerItem1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String keyword;
    private String type;
    private String name;
    private String img;

    private long bannerId;

//    双向一对多
//    @ManyToOne
//    @JoinColumn(name = "bannerId", insertable = false, updatable = false)
//    private Banner banner;

}
