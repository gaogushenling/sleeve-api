package com.robinstudio.sleeveapi.sample;

import com.robinstudio.sleeveapi.sample.condition.DianaCondition;
import com.robinstudio.sleeveapi.sample.hero.Camille;
import com.robinstudio.sleeveapi.sample.hero.Diana;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroConfiguration {
    // @Bean
    // public ISkill camille() {
    //     return new Camille("Camille", 18);
    // }

    @Bean
    @Conditional(DianaCondition.class)
    @ConditionalOnProperty(value="hero.condition", havingValue = "diana")
    public ISkill dianna() {
        return new Diana();
    }

}
