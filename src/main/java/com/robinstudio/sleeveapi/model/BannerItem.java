package com.robinstudio.sleeveapi.model;

import javax.persistence.*;

@Entity
public class BannerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String keyword;
    private String type;
    private String name;
    private String img;

    private long bannerId;
}
