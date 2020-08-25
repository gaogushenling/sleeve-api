package com.robinstudio.sleeveapi.api.v1;

import com.robinstudio.sleeveapi.core.interceptors.ScopeLevel;
import com.robinstudio.sleeveapi.exception.http.ForbiddenException;
import com.robinstudio.sleeveapi.exception.http.NotFoundException;
import com.robinstudio.sleeveapi.model.Banner;
import com.robinstudio.sleeveapi.sample.IConnect;
import com.robinstudio.sleeveapi.sample.ISkill;
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

    @Autowired
    private ISkill iSkill;

    // @Autowired
    // private ISkill irelia;
    //
    // @Autowired
    // private ISkill diana;
    //
    // @Autowired
    // private ISkill camille;

    @Autowired
    private IConnect iConnect;

    @GetMapping("/test")
    public String test() {
        iSkill.e();
        // irelia.q();
        // diana.r();
        // camille.r();
        // iConnect.connect();
        // return "abc";
        throw new ForbiddenException(10001);
    }

    @Autowired
    private BannerService bannerService;

    @GetMapping("/name/{name}")
    @ScopeLevel()
    public Banner getByName(@PathVariable @NotBlank String name) {
        Banner banner = bannerService.getByName(name);
        if(banner == null){
            throw new NotFoundException(30005);
        }
        return banner;
    }
}
