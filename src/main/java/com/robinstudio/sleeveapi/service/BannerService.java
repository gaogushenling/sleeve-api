package com.robinstudio.sleeveapi.service;

import com.robinstudio.sleeveapi.model.Banner;
import org.springframework.stereotype.Service;

@Service
public interface BannerService {
    Banner getByName(String name);
}
