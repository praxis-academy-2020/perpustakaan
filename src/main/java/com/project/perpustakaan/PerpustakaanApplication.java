package com.project.perpustakaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.project.perpustakaan.property.FileStorageProperties;

@EnableConfigurationProperties({ FileStorageProperties.class })

@SpringBootApplication
// @ComponentScan({"com.project"})

public class PerpustakaanApplication {
	public static void main(String[] args) {
		SpringApplication.run(PerpustakaanApplication.class, args);
	}
}