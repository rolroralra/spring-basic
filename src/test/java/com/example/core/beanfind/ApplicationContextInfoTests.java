package com.example.core.beanfind;

import com.example.core.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTests {

    private final AnnotationConfigApplicationContext applicationContext
        = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            BeanDefinition beanDefinition =
                applicationContext.getBeanDefinition(beanDefinitionName);

            System.out.println("name= " + beanDefinitionName + ", bean = " + bean);
            System.out.println("beanDefinition = " + beanDefinition);
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
                System.out.println("name = " + beanDefinitionName + ", bean = " + bean);
                System.out.println("beanDefinition = " + beanDefinition);
            }
        }
    }

    @ParameterizedTest(name = "{index}: role={0}")
    @ValueSource(ints = {BeanDefinition.ROLE_APPLICATION, BeanDefinition.ROLE_INFRASTRUCTURE, BeanDefinition.ROLE_SUPPORT})
    void findBeanGroupByRole(int role) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition
                = applicationContext.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == role) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", bean = " + bean);
                System.out.println("beanDefinition = " + beanDefinition);
            }
        }
    }
}
