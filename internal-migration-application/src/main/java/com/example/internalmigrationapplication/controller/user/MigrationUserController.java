package com.example.internalmigrationapplication.controller.user;

import com.example.migrationservice.application.user.MigrationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/migration/v1/user")
@RequiredArgsConstructor
public class MigrationUserController {
    private final MigrationUserService service;

    @PostMapping("/{userId}/agree")
    public MigrationUserResult agree(@PathVariable("userId") Long userId) {
        return MigrationUserResult.from(service.agree(userId));
    }

    @GetMapping("/{userId}")
    public MigrationUserResult findById(@PathVariable("userId") Long userId) {
        return MigrationUserResult.from(service.findById(userId));
    }
}
