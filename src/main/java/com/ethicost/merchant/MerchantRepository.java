package com.ethicost.merchant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vrum on 16/8/17.
 */
@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {

    @Query("SELECT m from Merchant m, Transaction t where t.description = m.merchantDescription and t.accountId in :accountIds")
    List<Merchant> findMerchantsByAccountList(List<String> accountIds);

}
