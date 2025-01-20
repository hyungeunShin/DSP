package com.example.legacydspapplication.application.campaign;

import com.example.legacydspapplication.domain.campaign.LegacyCampaign;
import com.example.legacydspapplication.domain.campaign.LegacyCampaignRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LegacyCampaignService {
    private final LegacyCampaignRepository repository;

    @Transactional
//    public LegacyCampaignResult create(String name, Long userId, Long budget) {
    public LegacyCampaign create(String name, Long userId, Long budget) {
        LegacyCampaign campaign = LegacyCampaign.of(name, userId, budget);
//        return LegacyCampaignResult.from(repository.save(campaign));
        return repository.save(campaign);
    }

    public LegacyCampaign find(Long id) {
//    public LegacyCampaignResult find(Long id) {
//        return LegacyCampaignResult.from(findById(id));
        return findById(id);
    }

    @Transactional
//    public LegacyCampaignResult updateName(Long id, String name) {
    public LegacyCampaign updateName(Long id, String name) {
        LegacyCampaign campaign = findById(id);
        campaign.updateName(name);
//        return LegacyCampaignResult.from(repository.save(campaign));
        return repository.save(campaign);
    }

    @Transactional
    public LegacyCampaign updateBudget(Long id, Long budget) {
//    public LegacyCampaignResult updateBudget(Long id, Long budget) {
        LegacyCampaign campaign = findById(id);
        campaign.updateBudget(budget);
//        return LegacyCampaignResult.from(repository.save(campaign));
        return repository.save(campaign);
    }

    @Transactional
//    public LegacyCampaignResult delete(Long id) {
    public LegacyCampaign delete(Long id) {
        LegacyCampaign campaign = findById(id);
        campaign.delete();
//        return LegacyCampaignResult.from(repository.save(campaign));
        return repository.save(campaign);
    }

    private LegacyCampaign findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
