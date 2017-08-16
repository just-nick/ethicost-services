package com.ethicost.service;

import com.ethicost.model.Merchant;
import com.ethicost.repository.MerchantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vrum on 16/8/17.
 */
@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MerchantService {

    private MerchantRepository merchantRepository;


    public Iterable<Merchant> findAllMerchants() {
        return merchantRepository.findAll();
    }
}
