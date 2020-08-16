package com.robinstudio.sleeveapi.model1;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@Table(name = "banner")
public class Banner1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String title;
    private String img;

//    导航属性
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "bannerId")
    private List<BannerItem1> items;

//    双向一对多 mappedBy 对应多方的导航属性
//    @OneToMany(mappedBy = "banner")
//    private List<BannerItem> items;
}
