package com.ck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ck.task.SpringTask;

@SpringBootApplication
@MapperScan("com.ck.dao")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		SpringTask.initial();
	}
}