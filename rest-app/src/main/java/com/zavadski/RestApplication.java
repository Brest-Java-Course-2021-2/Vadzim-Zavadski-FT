package com.zavadski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@SpringBootApplication
@PropertySource({"classpath:dao.properties"})
public class RestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(RestApplication.class, args);

    }
}
