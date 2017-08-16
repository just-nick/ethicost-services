package com.ethicost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/oauth")
public class OAuthController {

    private final OAuthService oAuthService;

    @Autowired
    public OAuthController(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @GetMapping("/token")
    @ResponseBody()
    public ResponseEntity<OAuthToken> getToken(@RequestParam(value = "accessCode", defaultValue = "") String accessCode) {
        return oAuthService.getToken(accessCode);
    }
}
