package com.example.springdemo.demos.web.demo;

import com.example.springdemo.constant.ResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/sse")
public class SseController {

    protected static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>(16);

    @GetMapping("/connect")
    public String connect(@RequestParam(name = "userId") String userId) {
        log.info("[{}]连接SSE", userId);
        SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitterMap.put(userId, sseEmitter);
        return ResultConstant.ResultMsg.success;
    }

    @GetMapping("/send")
    public String send(@RequestParam(name = "userId") String userId, @RequestParam(name = "msg") String msg) throws IOException {
        log.info("[{}]发送SSE消息，内容：{}", userId, msg);
        sseEmitterMap.get(userId).send(msg);
        return ResultConstant.ResultMsg.success;
    }

    @GetMapping("/close")
    public String close(@RequestParam(name = "userId") String userId) {
        log.info("[{}]断开连接SSE", userId);
        sseEmitterMap.remove(userId);
        return ResultConstant.ResultMsg.success;
    }

}
