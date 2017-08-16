package com.ethicost.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    @RequestMapping("/hello-world")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/merchant")
    @ResponseBody
    public String test() {
        return "This is merchant.";
    }
}
