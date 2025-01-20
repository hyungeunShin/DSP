package com.example.legacydspapplication.controller.user;

import com.example.legacydspapplication.application.user.LegacyUserService;
import com.example.legacydspapplication.controller.user.dto.LegacyUserCreateRequest;
import com.example.legacydspapplication.controller.user.dto.LegacyUserResponse;
import com.example.legacydspapplication.controller.user.dto.LegacyUserUpdateNameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/v1")
@RequiredArgsConstructor
public class LegacyUserController {
    private final LegacyUserService service;

    @PostMapping("")
    public LegacyUserResponse create(@RequestBody LegacyUserCreateRequest dto) {
        return LegacyUserResponse.from(service.create(dto.name()));
    }

    @GetMapping("/{id}")
    public LegacyUserResponse find(@PathVariable("id") Long id) {
        return LegacyUserResponse.from(service.find(id));
    }

    @PutMapping("/updateName")
    public LegacyUserResponse updateName(@RequestBody LegacyUserUpdateNameRequest dto) {
        return LegacyUserResponse.from(service.updateName(dto.id(), dto.name()));
    }

    @DeleteMapping("/{id}")
    public LegacyUserResponse delete(@PathVariable("id") Long id) {
        return LegacyUserResponse.from(service.delete(id));
    }
}
