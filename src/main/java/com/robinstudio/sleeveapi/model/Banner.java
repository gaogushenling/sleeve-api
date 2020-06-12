package com.robinstudio.sleeveapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String title;
    private String img;

    @OneToMany
    @JoinColumn(name = "bannerId")
    private List<BannerItem> items;
}
