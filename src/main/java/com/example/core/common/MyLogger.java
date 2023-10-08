package com.example.core.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class MyLogger {

    private String uuid;

    @Setter
    private String requestURL;

    @PostConstruct
    public void init() {
        uuid = java.util.UUID.randomUUID().toString();
        log.info("[{}] request scope bean create: {}", uuid, this);
    }

    @PreDestroy
    public void close() {
        log.info("[{}] request scope bean close: {}", uuid, this);
    }

    public void log(String message) {
        log.info("[{}][{}] {}", uuid, requestURL, message);
    }
}
