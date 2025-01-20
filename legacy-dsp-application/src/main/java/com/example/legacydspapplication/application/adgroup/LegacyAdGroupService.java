package com.example.legacydspapplication.application.adgroup;

import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;
import com.example.legacydspapplication.domain.adgroup.LegacyAdGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LegacyAdGroupService {
    private final LegacyAdGroupRepository repository;

    @Transactional
//    public LegacyAdGroupResult create(String name, Long userId, Long campaignId, String linkUrl) {
    public LegacyAdGroup create(String name, Long userId, Long campaignId, String linkUrl) {
        LegacyAdGroup group = LegacyAdGroup.of(name, userId, campaignId, linkUrl);
//        return LegacyAdGroupResult.from(repository.save(group));
        return repository.save(group);
    }

//    public LegacyAdGroupResult find(Long id) {
    public LegacyAdGroup find(Long id) {
//        return LegacyAdGroupResult.from(findById(id));
        return findById(id);
    }

    @Transactional
    public LegacyAdGroup updateName(Long id, String name) {
//    public LegacyAdGroupResult updateName(Long id, String name) {
        LegacyAdGroup group = findById(id);
        group.updateName(name);
//        return LegacyAdGroupResult.from(repository.save(group));
        return repository.save(group);
    }

    @Transactional
    public LegacyAdGroup updateLinkUrl(Long id, String linkUrl) {
//    public LegacyAdGroupResult updateBudget(Long id, String linkUrl) {
        LegacyAdGroup group = findById(id);
        group.updateLinkUrl(linkUrl);
//        return LegacyAdGroupResult.from(repository.save(group));
        return repository.save(group);
    }

    @Transactional
    public LegacyAdGroup delete(Long id) {
//    public LegacyAdGroupResult delete(Long id) {
        LegacyAdGroup group = findById(id);
        group.delete();
//        return LegacyAdGroupResult.from(repository.save(group));
        return repository.save(group);
    }

    private LegacyAdGroup findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
