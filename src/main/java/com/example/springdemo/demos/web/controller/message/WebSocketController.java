package com.example.springdemo.demos.web.controller.message;

import com.example.springdemo.constant.ResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webSocket")
public class WebSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/send")
    public String sendMessage(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        webSocketServer.sendOneMessage(userId, msg);
        return ResultConstant.ResultMsg.success;
    }

}
