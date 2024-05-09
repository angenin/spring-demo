package com.example.springdemo;

import cn.hutool.core.util.DesensitizedUtil;
import com.example.springdemo.channel.message.SmsService;
import com.example.springdemo.enums.SmsEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringDemoApplicationTests {

    @Autowired
    private SmsService smsService;

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Value("${password.test1}")
    private String password1;
    @Value("${password.test2}")
    private String password2;

    /**
     * 配置文件内容加密测试
     */
    @Test
    public void test() {
        log.info("password1: {}", password1);
        log.info("password2 {}", password2);
    }

    /**
     * 短信发送测试
     */
    @Test
    public void smsSendTest() {
        String phone = "16676871109";
        String carLicense = DesensitizedUtil.carLicense("粤ELX006");
        String service = "年审";
        String dayNum = "30";
        smsService.sendSms(SmsEnum.EXPIRATION_NOTICE, phone, Arrays.asList(carLicense, service, dayNum));
    }


}
