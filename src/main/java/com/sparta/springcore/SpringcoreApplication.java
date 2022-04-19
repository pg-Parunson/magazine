package com.sparta.springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing // 타임스탬프 찍을 떄 필요함
@ServletComponentScan // @WebServlet 어노테이션이 동작하게 함
@SpringBootApplication
public class SpringcoreApplication {
    @PostConstruct
    void started() { TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul")); }
    public static void main(String[] args) {
        SpringApplication.run(SpringcoreApplication.class, args);
    }
}