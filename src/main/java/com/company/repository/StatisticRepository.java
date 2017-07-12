package com.company.repository;

import com.company.model.Statistic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends CrudRepository<Statistic, Long> {
    Optional<List<Statistic>> findByAccountId(String AccountId);

    Optional<Statistic> findByAccountIdAndLongUrl(String accountId, String longUrl);
}
