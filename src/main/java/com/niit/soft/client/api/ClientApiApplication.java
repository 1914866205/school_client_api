package com.niit.soft.client.api;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tao
 */
//@EnableRabbit //开启基于注解的RabbitMQ模式
//@ComponentScan(basePackages = {"com.niit.soft.client.api"})
//@EnableAsync  //作用于启动类，放置在启动类上开启异步任务注解
//开启定时
@EnableScheduling
@SpringBootApplication
@MapperScan("com.niit.soft.client.api.mapper")
@MapperScan("com.niit.soft.client.api.errends.mapper")
@EnableJpaAuditing
@EnableCaching
public class ClientApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApiApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
