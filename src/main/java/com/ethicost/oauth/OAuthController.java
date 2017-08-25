package com.ethicost.oauth;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/oauth")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OAuthController {

    private final OAuthService oAuthService;

    @RequestMapping(value = "/token",method= RequestMethod.GET)
    @ResponseBody
    public String getToken(@RequestParam(value = "accessCode", defaultValue = "") String accessCode) {
        ResponseEntity<OAuthToken> accessCodeResponse =  oAuthService.getToken(accessCode);
        return accessCodeResponse.getBody().getAccessToken();
    }
}
