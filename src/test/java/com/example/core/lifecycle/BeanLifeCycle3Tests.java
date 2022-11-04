package com.example.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class BeanLifeCycle3Tests {
    static class LifeCycleConfig {

        @Bean
        public NetworkClient3 networkClient() {
            NetworkClient3 networkClient = new NetworkClient3();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient3 networkClient = applicationContext.getBean(NetworkClient3.class);
        applicationContext.close();

        System.out.println(networkClient.getUrl());
    }
}
