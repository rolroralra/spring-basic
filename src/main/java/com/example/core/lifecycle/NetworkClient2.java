package com.example.core.lifecycle;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NetworkClient2 {

    @Setter
    private String url;

    public NetworkClient2() {
        System.out.println("생성자 호출, url = " + url);
    }

    /**
     * 서비스 시작시 호출
     */
    public void connect() {
        System.out.println("connect: " + url);
    }
    public void call(String message) {
        System.out.println("call: " + url + ", message = " + message);
    }

    /**
     * 서비스 종료시 호출
     */
    public void disconnect() {
        System.out.println("close: " + url);
    }

    public void init() {
        System.out.println(this.getClass().getSimpleName() + ": init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println(this.getClass().getSimpleName() + ": close");
        disconnect();
    }

}
