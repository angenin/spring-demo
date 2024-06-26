package com.example.springdemo.controller.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
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
    public SseEmitter connect(@RequestParam(name = "userId") String userId) {
        log.info("[{}]连接SSE", userId);
        SseEmitter sseEmitter = new SseEmitter(0L);
        try {
            sseEmitter.send(SseEmitter.event().id(userId));
        } catch (IOException e) {
            log.error("创建连接失败 , {} " , e.getMessage());
        }
        sseEmitter.onCompletion(() -> log.info("connect onCompletion , {} 结束连接 ..." , userId));
        sseEmitter.onTimeout(() -> log.info("connect onTimeout , {} 连接超时 ..." , userId));
        sseEmitter.onError((throwable) -> log.error("connect onError , {} 连接异常 ..." , userId));
        sseEmitterMap.put(userId, sseEmitter);
        return sseEmitter;
    }

    @GetMapping("/send")
    public void send(@RequestParam(name = "userId") String userId, @RequestParam(name = "msg") String msg) throws IOException {
        log.info("[{}]发送SSE消息，内容：{}", userId, msg);
        sseEmitterMap.get(userId).send(msg);
    }

    @GetMapping("/close")
    public void close(@RequestParam(name = "userId") String userId) {
        log.info("[{}]断开连接SSE", userId);
        SseEmitter sseEmitter = sseEmitterMap.remove(userId);
        // 执行完毕，断开连接
        if (ObjectUtils.isNotEmpty(sseEmitter)) {
            sseEmitter.complete();
        }
    }

}
