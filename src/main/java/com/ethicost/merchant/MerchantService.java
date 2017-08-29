package com.ethicost.merchant;

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

    public int insertMerchant(MerchantRequest request) {
        Merchant dummy = new Merchant();
        dummy.setMerchantName(request.getMerchantName());
        dummy.setMerchantDescription(request.getMerchantDescription());
        dummy.setCategory(request.getCategory());
        dummy.setRating(request.getRating());
        return merchantRepository.save(dummy).getId();
    }

    public Iterable<Merchant> findAllMerchants() {
        return merchantRepository.findAll();
    }

    public List<Merchant> getMerchantTransactionsFor(List<String> accounts) {
        return merchantRepository.findMerchantsByAccountList(accounts);
    }
}
