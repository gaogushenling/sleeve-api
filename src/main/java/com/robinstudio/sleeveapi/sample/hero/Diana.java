package com.robinstudio.sleeveapi.sample.hero;

import org.springframework.stereotype.Component;

@Component
public class Diana implements ISkill{
    public Diana(){
        System.out.println("Diana");
    }

    public void q() {
        System.out.println("Diana Q");
    }

    public void w() {
        System.out.println("Diana W");
    }

    public void r() {
        System.out.println("Diana R");
    }

    public void e() {
        System.out.println("Diana E");
    }
}

