package com.example.core.web;

import com.example.core.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;

    private final ObjectProvider<MyLogger> myLoggerProvider;

    @GetMapping("/log-demo")
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        MyLogger myLogger = myLoggerProvider.getObject();

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");

        logDemoService.logic("testId");

        return "OK";
    }
}
