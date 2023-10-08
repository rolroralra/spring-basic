package com.example.core.scope;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class PrototypeBeanTests {
    @Scope(SCOPE_PROTOTYPE)
    static class PrototypeBean {

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
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean");
        PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);

        BeanDefinition beanDefinition = applicationContext.getBeanDefinition(
            "prototypeBeanTests.PrototypeBean");
        System.out.println("beanDefinition = " + beanDefinition);

        System.out.println("prototypeBean = " + prototypeBean);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean)
            .isNotSameAs(prototypeBean2);

        applicationContext.close();
    }
}
