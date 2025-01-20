package com.example.legacydspapplication.controller.keyword;

import com.example.legacydspapplication.application.keyword.LegacyKeywordService;
import com.example.legacydspapplication.controller.keyword.dto.LegacyKeywordCreateRequest;
import com.example.legacydspapplication.controller.keyword.dto.LegacyKeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keywords/v1")
@RequiredArgsConstructor
public class LegacyKeywordController {
    private final LegacyKeywordService service;

    @PostMapping("")
    public LegacyKeywordResponse create(@RequestBody LegacyKeywordCreateRequest dto) {
        return LegacyKeywordResponse.from(service.create(dto.text(), dto.adGroupId(), dto.userId()));
    }

    @GetMapping("/{id}")
    public LegacyKeywordResponse find(@PathVariable("id") Long id) {
        return LegacyKeywordResponse.from(service.find(id));
    }

    @DeleteMapping("/{id}")
    public LegacyKeywordResponse delete(@PathVariable("id") Long id) {
        return LegacyKeywordResponse.from(service.delete(id));
    }
}
