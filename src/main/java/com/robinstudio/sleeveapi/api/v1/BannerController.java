package com.robinstudio.sleeveapi.api.v1;

import com.robinstudio.sleeveapi.model.Banner;
import com.robinstudio.sleeveapi.sample.hero.Diana;
import com.robinstudio.sleeveapi.sample.hero.ISkill;
import com.robinstudio.sleeveapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/v1/banner")
@Validated
public class BannerController {
    // 属性注入
    // @Autowired
    // private Diana diana;

    // 构造注入
    // @Autowired
    // public BannerController(Diana diana) {
    //     this.diana = diana;
    // }

    // setter 注入
    // @Autowired
    // public void setDiana(Diana diana) {
    //     this.diana = diana;
    // }

    // @Autowired
    // private ISkill iSkill;

    @Autowired
    private ISkill irelia;

    @Autowired
    private ISkill diana;

    @GetMapping("/test")
    public String test() {
        // iSkill.e();
        irelia.q();
        diana.r();
        return "abc";
    }

    @Autowired
    private BannerService bannerService;

    @GetMapping("/name/{name}")
    public Banner getByName(@PathVariable @NotBlank String name) {
        Banner banner = bannerService.getByName(name);
        if(banner == null){
            // 自定义异常 week 12
            // throw new NotFoundException(30005)
        }
        return banner;
    }
}
