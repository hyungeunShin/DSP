package com.example.internalmigrationapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.migrationservice", "com.example.internalmigrationapplication"})
public class InternalMigrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(InternalMigrationApplication.class, args);
    }
}
