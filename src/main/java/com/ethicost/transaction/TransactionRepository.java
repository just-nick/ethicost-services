package com.ethicost.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    public List<Transaction> findAllByAccountIdIn(List<String> accountIds);

    public void deleteAllByAccountIdIn(List<String> accountIds);
}
