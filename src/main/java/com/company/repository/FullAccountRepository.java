package com.company.repository;

import com.company.model.FullAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FullAccountRepository extends CrudRepository<FullAccount, Long> {
    Optional<FullAccount> findByAccountId(String accountId);
    Optional<FullAccount> findByAccountIdAndPassword(String accountId, String password);
}
