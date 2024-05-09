package com.example.springdemo.channel.message;

import com.example.springdemo.enums.SmsEnum;

import java.util.List;

public interface SmsService {

    /**
     * 短信发送
     * @param smsEnum
     * @param phone
     * @param contentList
     */
    void sendSms(SmsEnum smsEnum, String phone, List<String> contentList);

    /**
     * 群发短信
     * @param smsEnum
     * @param phoneList
     * @param contentList
     */
    void sendSms(SmsEnum smsEnum, List<String> phoneList, List<String> contentList);

}
