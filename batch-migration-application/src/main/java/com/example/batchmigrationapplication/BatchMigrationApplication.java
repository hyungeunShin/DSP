package com.example.batchmigrationapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.batchmigrationapplication", "com.example.migrationservice"})
public class BatchMigrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchMigrationApplication.class, args);
    }
}
