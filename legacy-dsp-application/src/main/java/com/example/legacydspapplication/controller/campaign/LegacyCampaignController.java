package com.example.legacydspapplication.controller.campaign;

import com.example.legacydspapplication.application.campaign.LegacyCampaignService;
import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignCreateRequest;
import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignResult;
import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignUpdateBudgetRequest;
import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignUpdateNameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaigns/v1")
@RequiredArgsConstructor
public class LegacyCampaignController {
    private final LegacyCampaignService service;

    @PostMapping("")
    public LegacyCampaignResult create(@RequestBody LegacyCampaignCreateRequest dto) {
        return LegacyCampaignResult.from(service.create(dto.name(), dto.userId(), dto.budget()));
    }

    @GetMapping("/{id}")
    public LegacyCampaignResult find(@PathVariable("id") Long id) {
        return LegacyCampaignResult.from(service.find(id));
    }

    @PutMapping("/updateName")
    public LegacyCampaignResult updateName(@RequestBody LegacyCampaignUpdateNameRequest dto) {
        return LegacyCampaignResult.from(service.updateName(dto.id(), dto.name()));
    }

    @PutMapping("/updateBudget")
    public LegacyCampaignResult updateBudget(@RequestBody LegacyCampaignUpdateBudgetRequest dto) {
        return LegacyCampaignResult.from(service.updateBudget(dto.id(), dto.budget()));
    }

    @DeleteMapping("/{id}")
    public LegacyCampaignResult delete(@PathVariable("id") Long id) {
        return LegacyCampaignResult.from(service.delete(id));
    }
}
