package com.example.core.bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.core.annotation.scan.filter.MyExcludeComponent;
import com.example.core.annotation.scan.filter.MyIncludeComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

class ComponentFilterAppConfigTests {

    @Test
    void filterScan() {
        ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = applicationContext.getBean(BeanA.class);

        assertThat(beanA).isNotNull();

        assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
            .isThrownBy(() ->
                applicationContext.getBean(BeanB.class)
            );
    }
    @Configuration
    @ComponentScans({
        @ComponentScan(
            includeFilters = {
                @Filter(classes = MyIncludeComponent.class)
            },
            excludeFilters = {
                @Filter(classes = MyExcludeComponent.class),
//                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
            })
    })
    static class ComponentFilterAppConfig {

    }
}
