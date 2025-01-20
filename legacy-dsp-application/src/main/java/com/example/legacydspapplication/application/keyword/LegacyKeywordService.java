package com.example.legacydspapplication.application.keyword;

import com.example.legacydspapplication.domain.keyword.LegacyKeyword;
import com.example.legacydspapplication.domain.keyword.LegacyKeywordRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LegacyKeywordService {
    private final LegacyKeywordRepository repository;

    @Transactional
//    public LegacyKeywordResult create(String text, Long adGroupId, Long userId) {
    public LegacyKeyword create(String text, Long adGroupId, Long userId) {
        LegacyKeyword keyword = LegacyKeyword.of(text, adGroupId, userId);
//        return LegacyKeywordResult.from(repository.save(keyword));
        return repository.save(keyword);
    }

//    public LegacyKeywordResult find(Long id) {
    public LegacyKeyword find(Long id) {
//        return LegacyKeywordResult.from(findById(id));
        return findById(id);
    }

    @Transactional
//    public LegacyKeywordResult delete(Long id) {
    public LegacyKeyword delete(Long id) {
        LegacyKeyword keyword = findById(id);
        keyword.delete();
//        return LegacyKeywordResult.from(repository.save(keyword));
        return repository.save(keyword);
    }

    private LegacyKeyword findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
