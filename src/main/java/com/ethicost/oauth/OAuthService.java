package com.ethicost.oauth;

import lombok.extern.slf4j.Slf4j;
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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OAuthService {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String CODE = "code";

    private final Environment environment;

    private static Map<String, String> tokenMap = new HashMap<>();

    @Autowired
    public OAuthService(Environment environment) {
        this.environment = environment;
    }

    public ResponseEntity<OAuthToken> getToken(String accessCode) {

        String baseApiUrl = environment.getProperty("macquarie.apiUrl");
        String oauthTokenPath = environment.getProperty("macquarie.oauth.tokenPath");
        String oauthClientId = environment.getProperty("macquarie.oauth.clientId");
        String oauthClientSecret = environment.getProperty("macquarie.oauth.clientSecret");
        String oauthGrantType = environment.getProperty("macquarie.oauth.grantType");

        log.info("ID is ----->" + oauthClientId);
        log.info("Jecret is ----->" + oauthClientSecret);

        RestTemplate restTemplate = new RestTemplate();
        String apiPath = baseApiUrl + oauthTokenPath;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(CLIENT_ID, oauthClientId);
        headers.set(CLIENT_SECRET, oauthClientSecret);

        MultiValueMap<String, String> oauthInputData = new LinkedMultiValueMap<>();
        oauthInputData.add(GRANT_TYPE, oauthGrantType);
        oauthInputData.add(CODE, accessCode);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(oauthInputData, headers);

        ResponseEntity<OAuthToken> response = restTemplate.postForEntity(apiPath, entity, OAuthToken.class);
        tokenMap.put(response.getBody().getAccessToken(), response.getBody().getRefreshToken());
        return response;
    }


    public ResponseEntity<OAuthToken> getTokenByRefreshToken(String refreshToken) {

        String baseApiUrl = environment.getProperty("macquarie.apiUrl");
        String oauthTokenPath = environment.getProperty("macquarie.oauth.tokenPath");
        String oauthClientId = environment.getProperty("macquarie.oauth.clientId");
        String oauthClientSecret = environment.getProperty("macquarie.oauth.clientSecret");

        RestTemplate restTemplate = new RestTemplate();
        String apiPath = baseApiUrl + oauthTokenPath;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(CLIENT_ID, oauthClientId);
        headers.set(CLIENT_SECRET, oauthClientSecret);

        MultiValueMap<String, String> oauthInputData = new LinkedMultiValueMap<>();
        oauthInputData.add(GRANT_TYPE, "refresh_token");
        oauthInputData.add(REFRESH_TOKEN, refreshToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(oauthInputData, headers);

        return restTemplate.postForEntity(apiPath, entity, OAuthToken.class);
    }

    public String refreshToken(String existingToken) {

        if (!tokenMap.containsKey(existingToken)) {
            return null;
        }

        String refreshToken = tokenMap.get(existingToken);
        ResponseEntity<OAuthToken> accessCodeResponse = this.getTokenByRefreshToken(refreshToken);
        String updatedToken = accessCodeResponse.getBody().getAccessToken();
        tokenMap.put(existingToken, accessCodeResponse.getBody().getRefreshToken());
        return updatedToken;
    }

}
