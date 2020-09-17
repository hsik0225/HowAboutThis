package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class SignInController {

    private final SignInService signInService;

    @PostMapping("/api/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody @Validated({Default.class, SignIn.class}) User user) throws URISyntaxException {
        signInService.checkUserByEmail(user.getEmail());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/api/main"));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
