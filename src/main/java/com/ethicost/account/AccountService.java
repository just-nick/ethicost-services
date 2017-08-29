package com.ethicost.account;

import com.ethicost.oauth.OAuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountService {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CODE = "code";
    private static final String AUTHORIZATION = "Authorization";

    private final Environment environment;

    @Autowired
    public AccountService(Environment environment) {
        this.environment = environment;
    }

    public ResponseEntity<MacAccountResponse> getAccounts(String authorizationHeader) {

        String baseApiUrl = environment.getProperty("macquarie.apiUrl");
        String clientId = environment.getProperty("macquarie.oauth.clientId");
        String accountPath = environment.getProperty("macquarie.api.accountPath");
        RestTemplate restTemplate = new RestTemplate();
        String apiPath = baseApiUrl + accountPath;

        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(CLIENT_ID, clientId);
        headers.set(AUTHORIZATION, authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        ResponseEntity<MacAccountResponse> accounts = restTemplate.exchange(apiPath, HttpMethod.GET, entity, MacAccountResponse.class);
        return accounts;
    }
}
