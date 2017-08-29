package com.ethicost.oauth;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/oauth")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OAuthController {

    private final OAuthService oAuthService;

    private static Map<String, OAuthToken> tokenMap = new HashMap<>();

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ResponseBody
    public OAuthToken getToken(@RequestParam(value = "accessCode", defaultValue = "") String accessCode) {

        OAuthToken existedToken = tokenMap.get(accessCode);
        if (existedToken == null) {
            ResponseEntity<OAuthToken> accessCodeResponse = oAuthService.getToken(accessCode);
            OAuthToken storedToken = accessCodeResponse.getBody();

            tokenMap.put(accessCode, storedToken);
            return accessCodeResponse.getBody();
        } else {

            String refreshToken = existedToken.getRefreshToken();
            ResponseEntity<OAuthToken> accessCodeResponse = oAuthService.getTokenByRefreshToken(refreshToken);
            tokenMap.put(accessCode, accessCodeResponse.getBody());
            return accessCodeResponse.getBody();
        }
    }
}
