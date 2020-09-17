package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.user.domain.GitHubProperty;
import com.naru.howaboutthis.user.domain.GitHubUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OAuthController {

    private final GitHubProperty gitHubProperty;

    @GetMapping("/code")
    public ResponseEntity<Object> getCode() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(GitHubUrl.CODE.getUrl())
                .queryParam("client_id", gitHubProperty.getClientId())
                .queryParam("scope", "user")
                .build()
                .toUri();

        headers.setLocation(uri);

        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    @GetMapping("")
    public ResponseEntity<Object> oauth(@RequestParam String code, HttpServletResponse response) throws IOException {
        response.sendRedirect("/main");
        return new ResponseEntity<>("OAuth Success", HttpStatus.FOUND);
    }
}
