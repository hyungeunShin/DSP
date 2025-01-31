package com.example.legacydspapplication.controller.user;

import com.example.legacydspapplication.application.user.LegacyUserService;
import com.example.legacydspapplication.controller.user.dto.LegacyUserCreateRequest;
import com.example.legacydspapplication.controller.user.dto.LegacyUserResult;
import com.example.legacydspapplication.controller.user.dto.LegacyUserUpdateNameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/v1")
@RequiredArgsConstructor
public class LegacyUserController {
    private final LegacyUserService service;

    @PostMapping("")
    public LegacyUserResult create(@RequestBody LegacyUserCreateRequest dto) {
        return LegacyUserResult.from(service.create(dto.name()));
    }

    @GetMapping("/{id}")
    public LegacyUserResult find(@PathVariable("id") Long id) {
        return LegacyUserResult.from(service.find(id));
    }

    @PutMapping("/updateName")
    public LegacyUserResult updateName(@RequestBody LegacyUserUpdateNameRequest dto) {
        return LegacyUserResult.from(service.updateName(dto.id(), dto.name()));
    }

    @DeleteMapping("/{id}")
    public LegacyUserResult delete(@PathVariable("id") Long id) {
        return LegacyUserResult.from(service.delete(id));
    }
}
