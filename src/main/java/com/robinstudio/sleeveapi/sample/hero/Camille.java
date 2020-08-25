package com.robinstudio.sleeveapi.sample.hero;

import com.robinstudio.sleeveapi.sample.ISkill;

public class Camille implements ISkill {
    private String name;
    private Integer age;

    public Camille(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Camille() {
        // System.out.println("Camille");
    }

    public void q() {
        System.out.println("Camille Q");
    }

    public void w() {
        System.out.println("Camille W");
    }

    public void r() {
        System.out.println("Camille R");
    }

    public void e() {
        System.out.println("Camille E");
    }
}
