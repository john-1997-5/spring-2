package com.johnson.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("").setViewName("home");
        //registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/courses").setViewName("courses");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/holidays").setViewName("holidays");
    }
}
