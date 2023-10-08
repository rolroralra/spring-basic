package com.example.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonBeanTests {

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println(this.getClass().getSimpleName() + " init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println(this.getClass().getSimpleName() + " destroy");
        }
    }

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(SingletonBean.class);

        System.out.println("singletonBean find");
        SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);

        System.out.println("singletonBean2 find");
        SingletonBean singletonBean2 = applicationContext.getBean(SingletonBean.class);

        BeanDefinition beanDefinition = applicationContext.getBeanDefinition("singletonBeanTests.SingletonBean");
        System.out.println("beanDefinition = " + beanDefinition);

        System.out.println("singletonBean = " + singletonBean);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean)
            .isSameAs(singletonBean2);

        applicationContext.close();
    }
}
