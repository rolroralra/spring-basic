package com.example.core.config;

import com.example.core.DemoApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
    basePackageClasses = {DemoApplication.class},
    excludeFilters = {@Filter(/*type = FilterType.ANNOTATION, */classes = Configuration.class)}
)
public class AutoAppConfig {

}
