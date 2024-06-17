package com.example.churchFucTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ChurchFucTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChurchFucTestApplication.class, args);
	}

}
