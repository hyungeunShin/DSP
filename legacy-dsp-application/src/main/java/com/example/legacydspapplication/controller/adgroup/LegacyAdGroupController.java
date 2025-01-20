package com.example.legacydspapplication.controller.adgroup;

import com.example.legacydspapplication.application.adgroup.LegacyAdGroupService;
import com.example.legacydspapplication.controller.adgroup.dto.LegacyAdGroupCreateRequest;
import com.example.legacydspapplication.controller.adgroup.dto.LegacyAdGroupResponse;
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
    public LegacyAdGroupResponse create(@RequestBody LegacyAdGroupCreateRequest dto) {
        return LegacyAdGroupResponse.from(service.create(dto.name(), dto.userId(), dto.campaignId(), dto.linkUrl()));
    }

    @GetMapping("/{id}")
    public LegacyAdGroupResponse find(@PathVariable("id") Long id) {
        return LegacyAdGroupResponse.from(service.find(id));
    }

    @PutMapping("/updateName")
    public LegacyAdGroupResponse updateName(@RequestBody LegacyAdGroupUpdateNameRequest dto) {
        return LegacyAdGroupResponse.from(service.updateName(dto.id(), dto.name()));
    }

    @PutMapping("/updateLinkUrl")
    public LegacyAdGroupResponse updateBudget(@RequestBody LegacyAdGroupUpdateLinkUrlRequest dto) {
        return LegacyAdGroupResponse.from(service.updateLinkUrl(dto.id(), dto.linkUrl()));
    }

    @DeleteMapping("/{id}")
    public LegacyAdGroupResponse delete(@PathVariable("id") Long id) {
        return LegacyAdGroupResponse.from(service.delete(id));
    }
}
