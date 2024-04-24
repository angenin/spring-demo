package com.example.springdemo.controller.system;

import com.example.springdemo.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检测
 */
@RestController
public class HealthController extends BaseController {

    @GetMapping("/")
    public String health() {
        return "success";
    }

}