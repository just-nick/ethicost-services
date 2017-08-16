package com.ethicost.merchant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vrum on 16/8/17.
 */
@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MerchantService {

    private MerchantRepository merchantRepository;

    public int insertMerchant() {
        Merchant dummy = new Merchant();
        dummy.setMerchantName("Macquarie");
        return merchantRepository.save(dummy).getId();
    }

    public Iterable<Merchant> findAllMerchants() {
        return merchantRepository.findAll();
    }
}
