package com.naru.howaboutthis.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class GitHubProperty {

    @JsonProperty("code")
    private String code;

    @Value("${github.client-id}")
    @JsonProperty("client_id")
    private String clientId;

    @Value("${github.client-secret}")
    @JsonProperty("client_secret")
    private String clientSecret;
}
