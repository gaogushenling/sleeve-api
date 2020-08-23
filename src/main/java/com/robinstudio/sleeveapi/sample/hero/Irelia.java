package com.robinstudio.sleeveapi.sample.hero;

import org.springframework.stereotype.Component;

@Component
public class Irelia implements ISkill {
    public Irelia() {
        // System.out.println("Irelia");
    }

    public void q() {
        System.out.println("Irelia Q");
    }

    public void w() {
        System.out.println("Irelia W");
    }

    public void r() {
        System.out.println("Irelia R");
    }

    public void e() {
        System.out.println("Irelia E");
    }
}
