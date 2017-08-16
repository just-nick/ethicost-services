package com.ethicost.controller;

import com.ethicost.merchant.Merchant;
import com.ethicost.merchant.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class SampleController {

    private MerchantService merchantService;

    @RequestMapping("/hello-world")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

    @RequestMapping("/merchant")
    @ResponseBody
    public String test() {
        return "This is merchant.";
    }

    @RequestMapping("/merchant/test")
    @ResponseBody
    public String test1() {
        return String.valueOf(merchantService.insertMerchant());
    }

    @RequestMapping("/merchant/all")
    @ResponseBody
    public String getAllMerchants() {
        Iterable<Merchant> merchants = merchantService.findAllMerchants();


        if (merchants.iterator().hasNext()) {
            return merchants.iterator().next().getMerchantName();
        }
        return "No merchants found";
    }


}
