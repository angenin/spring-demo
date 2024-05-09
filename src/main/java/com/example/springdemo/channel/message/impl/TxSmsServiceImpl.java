package com.example.springdemo.channel.message.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.example.springdemo.channel.message.SmsService;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.enums.SmsEnum;
import com.example.springdemo.exception.ServiceException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class TxSmsServiceImpl implements SmsService {

    private static final String SIGN_NAME = "荣盛汽修公众号";
    private static final String SMS_SDK_APP_ID = "1400908835";
    private static final String SECRET_ID = "AKIDoTuQuFtPp9HD5pLfpqgN9qeTQMBqIWtE";
    private static final String SECRET_KEY = "PsId9e1UuCMPK8pbRLDD885rV5UgfcTB";

    @Override
    public void sendSms(SmsEnum smsEnum, String phone, List<String> contentList) {
        sendSms(smsEnum, Arrays.asList(phone), contentList);
    }

    @Override
    public void sendSms(SmsEnum smsEnum, List<String> phoneList, List<String> contentList) {
        log.info("【腾讯云】发送短信：{}，内容：{}", JSON.toJSONString(phoneList), JSON.toJSONString(contentList));
        try {
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "", clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppId(SMS_SDK_APP_ID);
            req.setSignName(SIGN_NAME);
            req.setPhoneNumberSet(phoneList.toArray(new String[0]));
            req.setTemplateId(smsEnum.getTemplateId());
            req.setTemplateParamSet(contentList.toArray(new String[0]));
            SendSmsResponse resp = client.SendSms(req);
            log.info("【腾讯云】短信发送结果：{}");
        } catch (Exception e) {
            log.error("【腾讯云】发送短信失败，异常：", e);
            throw new ServiceException(ResultEnum.SMS_SEND_FAIL);
        }
    }

}