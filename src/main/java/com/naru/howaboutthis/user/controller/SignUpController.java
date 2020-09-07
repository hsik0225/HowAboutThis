package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.user.domain.Policy;
import com.naru.howaboutthis.user.domain.PolicySingleton;
import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.groups.Default;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RequiredArgsConstructor

// @RequestParam 이나 @PatVariable을 메소드에서 Validation할 수 있게 한다
@Validated

// 기본 인코딩 형식은 "ISO-8869-1"이다. "ISO-8869-1"은 한글을 읽을 수 없으므로 "UTF-8"로 변경
@RequestMapping(value = "/api/users", produces = {"application/json; charset=UTF-8"})
@RestController
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/policy")
    public ResponseEntity<Policy> policy() {
        return ResponseEntity.ok(PolicySingleton.getInstance());
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody @Validated({Default.class, SignUp.class}) User user) throws URISyntaxException {
        signUpService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("/api/main"));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Void> canUseEmail(@PathVariable @Email String email) {
        signUpService.isDuplicated(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
