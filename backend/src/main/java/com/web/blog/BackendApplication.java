package com.web.blog;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan(basePackages = {"com.web.blog.mapper"})
@SpringBootApplication

public class BackendApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
