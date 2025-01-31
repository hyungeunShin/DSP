package com.example.legacydspapplication.controller.keyword;

import com.example.legacydspapplication.application.keyword.LegacyKeywordService;
import com.example.legacydspapplication.controller.keyword.dto.LegacyKeywordCreateRequest;
import com.example.legacydspapplication.controller.keyword.dto.LegacyKeywordResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keywords/v1")
@RequiredArgsConstructor
public class LegacyKeywordController {
    private final LegacyKeywordService service;

    @PostMapping("")
    public LegacyKeywordResult create(@RequestBody LegacyKeywordCreateRequest dto) {
        return LegacyKeywordResult.from(service.create(dto.text(), dto.adGroupId(), dto.userId()));
    }

    @GetMapping("/{id}")
    public LegacyKeywordResult find(@PathVariable("id") Long id) {
        return LegacyKeywordResult.from(service.find(id));
    }

    @DeleteMapping("/{id}")
    public LegacyKeywordResult delete(@PathVariable("id") Long id) {
        return LegacyKeywordResult.from(service.delete(id));
    }
}
