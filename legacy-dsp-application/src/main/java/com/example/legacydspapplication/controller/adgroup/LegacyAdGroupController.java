package com.example.legacydspapplication.controller.adgroup;

import com.example.legacydspapplication.application.adgroup.LegacyAdGroupService;
import com.example.legacydspapplication.controller.adgroup.dto.LegacyAdGroupCreateRequest;
import com.example.legacydspapplication.controller.adgroup.dto.LegacyAdGroupResult;
import com.example.legacydspapplication.controller.adgroup.dto.LegacyAdGroupUpdateLinkUrlRequest;
import com.example.legacydspapplication.controller.adgroup.dto.LegacyAdGroupUpdateNameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adgroups/v1")
@RequiredArgsConstructor
public class LegacyAdGroupController {
    private final LegacyAdGroupService service;

    @PostMapping("")
    public LegacyAdGroupResult create(@RequestBody LegacyAdGroupCreateRequest dto) {
        return LegacyAdGroupResult.from(service.create(dto.name(), dto.userId(), dto.campaignId(), dto.linkUrl()));
    }

    @GetMapping("/{id}")
    public LegacyAdGroupResult find(@PathVariable("id") Long id) {
        return LegacyAdGroupResult.from(service.find(id));
    }

    @PutMapping("/updateName")
    public LegacyAdGroupResult updateName(@RequestBody LegacyAdGroupUpdateNameRequest dto) {
        return LegacyAdGroupResult.from(service.updateName(dto.id(), dto.name()));
    }

    @PutMapping("/updateLinkUrl")
    public LegacyAdGroupResult updateBudget(@RequestBody LegacyAdGroupUpdateLinkUrlRequest dto) {
        return LegacyAdGroupResult.from(service.updateLinkUrl(dto.id(), dto.linkUrl()));
    }

    @DeleteMapping("/{id}")
    public LegacyAdGroupResult delete(@PathVariable("id") Long id) {
        return LegacyAdGroupResult.from(service.delete(id));
    }
}
