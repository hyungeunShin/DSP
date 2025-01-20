package com.example.legacydspapplication.controller.campaign;

import com.example.legacydspapplication.application.campaign.LegacyCampaignService;
import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignCreateRequest;
import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignResponse;
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
    public LegacyCampaignResponse create(@RequestBody LegacyCampaignCreateRequest dto) {
        return LegacyCampaignResponse.from(service.create(dto.name(), dto.userId(), dto.budget()));
    }

    @GetMapping("/{id}")
    public LegacyCampaignResponse find(@PathVariable("id") Long id) {
        return LegacyCampaignResponse.from(service.find(id));
    }

    @PutMapping("/updateName")
    public LegacyCampaignResponse updateName(@RequestBody LegacyCampaignUpdateNameRequest dto) {
        return LegacyCampaignResponse.from(service.updateName(dto.id(), dto.name()));
    }

    @PutMapping("/updateBudget")
    public LegacyCampaignResponse updateBudget(@RequestBody LegacyCampaignUpdateBudgetRequest dto) {
        return LegacyCampaignResponse.from(service.updateBudget(dto.id(), dto.budget()));
    }

    @DeleteMapping("/{id}")
    public LegacyCampaignResponse delete(@PathVariable("id") Long id) {
        return LegacyCampaignResponse.from(service.delete(id));
    }
}
