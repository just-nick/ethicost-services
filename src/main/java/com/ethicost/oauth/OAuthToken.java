package com.ethicost.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthToken {

    @JsonProperty("access_token")
    private String accessToken;
}
