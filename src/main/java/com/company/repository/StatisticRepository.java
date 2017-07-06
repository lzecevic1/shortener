package com.company.repository;

import com.company.model.Statistic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticRepository extends CrudRepository<Statistic, Long> {
    List<Statistic> findByAccountId(String AccountId);
    Statistic findByAccountIdAndLongUrl(String accountId, String longUrl);
}
