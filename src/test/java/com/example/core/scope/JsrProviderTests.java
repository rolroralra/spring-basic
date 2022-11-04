package com.example.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class JsrProviderTests {

    @RequiredArgsConstructor
    static class SingletonBean {

        private final Provider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        @Getter
        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println(this.getClass().getSimpleName() + " init... " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println(this.getClass().getSimpleName() + " destroy");
        }
    }

    @Test
    void prototypeBeanProvider() {
        ConfigurableApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);

        SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
        assertThat(singletonBean.logic()).isEqualTo(1);

        SingletonBean singletonBean2 = applicationContext.getBean(SingletonBean.class);
        assertThat(singletonBean2.logic()).isEqualTo(1);

        applicationContext.close();
    }
}
