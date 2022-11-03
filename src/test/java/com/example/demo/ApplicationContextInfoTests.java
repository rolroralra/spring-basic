package com.example.demo;

import com.example.demo.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTests {

    private final AnnotationConfigApplicationContext applicationContext
        = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            System.out.println("name= " + beanDefinitionName + ", bean = " + bean);
//            System.out.println(applicationContext.getBeanDefinition(beanDefinitionName));
        }
    }

    @Test
    @DisplayName("Application Bean 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition
                = applicationContext.getBeanDefinition(beanDefinitionName);


            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
                System.out.println("beanDefinition = " + beanDefinition);
            }
        }
    }



}
