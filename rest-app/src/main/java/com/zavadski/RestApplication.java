package com.zavadski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@PropertySource({"classpath:dao.properties"})
public class RestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RestApplication.class, args);

        // creates a FileInputStream from file newProp.txt to load it into the new properties object
        FileInputStream propFile = new FileInputStream("newProp.txt");

        // initializes p with the current set of system properties
        Properties p = new Properties(System.getProperties());

        // loads additional properties into p from the file newProp.txt
        p.load(propFile);

        // set the new system properties
        System.setProperties(p);

    }
}
