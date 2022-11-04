package com.example.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class BeanLifeCycle2Tests {
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient2 networkClient() {
            NetworkClient2 networkClient = new NetworkClient2();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient2 networkClient = applicationContext.getBean(NetworkClient2.class);
        applicationContext.close();

        System.out.println(networkClient.getUrl());
    }
}
