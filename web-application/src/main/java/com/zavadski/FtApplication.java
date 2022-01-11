package com.zavadski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:dao.properties"})
public class FtApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtApplication.class, args);
    }
}
