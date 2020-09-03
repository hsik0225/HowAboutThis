package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.exception.DuplicateEmailException;
import com.naru.howaboutthis.user.domain.Policy;
import com.naru.howaboutthis.user.domain.PolicySingleton;
import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/users")
@RestController
public class SignUpController {

    private final SignupService signupService;

    public SignUpController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("/policy")
    public Policy policy() {
        return PolicySingleton.getInstance();
    }

    @PostMapping("/signup/email-check")
    public ResponseEntity<Boolean> canUseEmail(String email) {
        return new ResponseEntity<>(signupService.canUseEmail(email), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody User user) {
        signupService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
