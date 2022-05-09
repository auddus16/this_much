package com.project.thismuch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories(basePackages ={"com.project.thismuch.myongyeon.*"})
@EntityScan(basePackages ={"com.project.thismuch.models.*"})
@ComponentScan(basePackages= {"com.project.thismuch.moreInfo.*"})
public class ThismuchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThismuchApplication.class, args);
	}

}
