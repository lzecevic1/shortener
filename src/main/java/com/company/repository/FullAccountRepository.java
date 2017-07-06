package com.company.repository;


import com.company.model.FullAccount;
import org.springframework.data.repository.CrudRepository;

public interface FullAccountRepository extends CrudRepository<FullAccount, Long> {
    FullAccount findByAccountId(String accountId);
    FullAccount findByAccountIdAndPassword(String accountId, String password);
}
