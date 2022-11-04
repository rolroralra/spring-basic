package com.example.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonBeanTests {

    @Scope("singleton")
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
        ConfigurableApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(SingletonBean.class);

        System.out.println("singletonBean find");
        SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);

        System.out.println("singletonBean2 find");
        SingletonBean singletonBean2 = applicationContext.getBean(SingletonBean.class);

        System.out.println("singletonBean = " + singletonBean);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean).isSameAs(singletonBean2);

        applicationContext.close();
    }
}
