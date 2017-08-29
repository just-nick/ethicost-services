package com.ethicost.merchant;

import com.ethicost.oauth.OAuthToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

/**
 * Created by vrum on 29/8/17.
 */
@RestController
@RequestMapping("/api/merchant")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MerchantController {

    private final MerchantService merchantService ;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Merchant> getMerchants() {
        Iterable<Merchant> merchants = merchantService.findAllMerchants();
        return merchants;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public int insertMerchants(@RequestBody @Valid MerchantRequest request) throws Exception {
        int id = merchantService.insertMerchant(request);
        return id;
    }




}
