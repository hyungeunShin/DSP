package com.example.gradualmigrationapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.migrationservice", "com.example.gradualmigrationapplication"})
public class GradualMigrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(GradualMigrationApplication.class, args);
	}
}
