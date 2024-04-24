package com.example.springdemo.controller.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webSocket")
public class WebSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/send")
    public void sendMessage(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        webSocketServer.sendOneMessage(userId, msg);
    }

}
