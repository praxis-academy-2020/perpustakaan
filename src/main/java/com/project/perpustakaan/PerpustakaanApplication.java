package com.project.perpustakaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.project"})
public class PerpustakaanApplication {
	public static void main(String[] args) {
		SpringApplication.run(PerpustakaanApplication.class, args);
	}
}
