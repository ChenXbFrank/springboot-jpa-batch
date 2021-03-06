package com.frank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @EnableJpaRepositories(basePackages = “xxx.xxx.xxx”) //扫描 @Repository 注解；
 * @ComponentScan(basePackages = “xxx.xxx.xxx”) //扫描 @Controller、@Service 注解；
 * @EntityScan(basePackages = “xxx.xxx.xxx”) //扫描 @Entity 注解；
 */
@SpringBootApplication
public class SpingbootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpingbootJpaApplication.class, args);
    }

}
