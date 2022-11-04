package com.example.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class BeanLifeCycleTest {
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient networkClient = applicationContext.getBean(NetworkClient.class);
        applicationContext.close();
    }
}
