package com.example.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class BeanLifeCycle1Tests {
    static class LifeCycleConfig {

        @Bean
        public NetworkClient1 networkClient() {
            NetworkClient1 networkClient = new NetworkClient1();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient1 networkClient = applicationContext.getBean(NetworkClient1.class);
        applicationContext.close();

        System.out.println(networkClient.getUrl());
    }
}
