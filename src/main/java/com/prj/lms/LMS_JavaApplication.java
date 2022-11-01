package com.prj.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LMS_JavaApplication extends SpringBootServletInitializer {
    //스프링 부트 외장 톰캣으로 이용하기 위함.
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LMS_JavaApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LMS_JavaApplication.class, args);
    }

}
