package com.example.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class LookupV3Tests {

   @RequiredArgsConstructor
   static abstract class SingletonBean {

       @Lookup
       abstract public PrototypeBean getPrototypeBean();

       public int logic() {
           PrototypeBean prototypeBean = getPrototypeBean();
           prototypeBean.addCount();
           return prototypeBean.getCount();
       }
   }

   @Getter
   @Scope("prototype")
   static class PrototypeBean {

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
   void lookup() {
       ConfigurableApplicationContext applicationContext
           = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);

       SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
       assertThat(singletonBean.logic()).isEqualTo(1);

       SingletonBean singletonBean2 = applicationContext.getBean(SingletonBean.class);
       assertThat(singletonBean2.logic()).isEqualTo(1);

       assertThat(singletonBean)
           .isSameAs(singletonBean2);

       assertThat(singletonBean.getPrototypeBean())
           .isNotSameAs(singletonBean2.getPrototypeBean());

       assertThat(singletonBean.getPrototypeBean())
           .isNotSameAs(singletonBean.getPrototypeBean());

       assertThat(singletonBean2.getPrototypeBean())
           .isNotSameAs(singletonBean2.getPrototypeBean());

       applicationContext.close();
   }
}
