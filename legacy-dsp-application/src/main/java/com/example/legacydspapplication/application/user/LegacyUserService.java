package com.example.legacydspapplication.application.user;

import com.example.legacydspapplication.domain.user.LegacyUser;
import com.example.legacydspapplication.domain.user.LegacyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LegacyUserService {
    private final LegacyUserRepository repository;

    @Transactional
    public LegacyUser create(String name) {
//    public LegacyUserResult create(String name) {
//        LegacyUser user = LegacyUser.of(name);
//        return LegacyUserResult.from(repository.save(user));
        return repository.save(LegacyUser.of(name));
    }

//    public LegacyUserResult find(Long id) {
    public LegacyUser find(Long id) {
//        return LegacyUserResult.from(findById(id));
        return findById(id);
    }

    @Transactional
    public LegacyUser updateName(Long id, String name) {
//    public LegacyUserResult updateName(Long id, String name) {
        LegacyUser user = findById(id);
        user.updateName(name);
        return repository.save(user);
//        return LegacyUserResult.from(repository.save(user));
    }

    @Transactional
//    public LegacyUserResult delete(Long id) {
    public LegacyUser delete(Long id) {
        LegacyUser user = findById(id);
        user.delete();
        return repository.save(user);
//        return LegacyUserResult.from(repository.save(user));
    }

    private LegacyUser findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
