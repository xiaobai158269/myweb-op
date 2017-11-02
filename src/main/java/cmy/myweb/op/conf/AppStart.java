package cmy.myweb.op.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "cmy.myweb.op")
@ImportResource(value = "classpath:conf/dubbo/dubbo-myweb-op.xml")
@SpringBootApplication
public class AppStart extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppStart.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppStart.class);
    }
}