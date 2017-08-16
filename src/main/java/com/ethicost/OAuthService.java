package com.ethicost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CODE = "code";

    private final Environment environment;

    @Autowired
    public OAuthService(Environment environment) {
        this.environment = environment;
    }

    public ResponseEntity<OAuthToken> getToken(String accessCode) {

        String baseApiUrl = environment.getProperty("macquarie.api");
        String oauthTokenPath = environment.getProperty("macquarie.oauth.token");
        String oauthClientId = environment.getProperty("macquarie.oauth.clinetId");
        String oauthClientSecret = environment.getProperty("macquarie.oauth.clientSecret");
        String oauthGrantType = environment.getProperty("macquarie.oauth.grantType");


        RestTemplate restTemplate = new RestTemplate();
        String apiPath = baseApiUrl + oauthTokenPath;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(CLIENT_ID, oauthClientId);
        headers.set(CLIENT_SECRET, oauthClientSecret);

        MultiValueMap<String, String> oauthInputData = new LinkedMultiValueMap<>();
        oauthInputData.add(GRANT_TYPE, oauthGrantType);
        oauthInputData.add(CODE, accessCode);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(oauthInputData, headers);
        return restTemplate.postForEntity(apiPath, entity, OAuthToken.class);
    }
}
