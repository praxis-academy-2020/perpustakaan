package com.project.perpustakaan;

import javax.annotation.Resource;

import com.project.perpustakaan.service.FilesStorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.project"})
public class PerpustakaanApplication implements CommandLineRunner {
	@Resource
    FilesStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(PerpustakaanApplication.class, args);
	}

	@Override
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
