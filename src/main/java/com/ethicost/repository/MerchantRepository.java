package com.ethicost.repository;

import com.ethicost.model.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vrum on 16/8/17.
 */
@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
}
