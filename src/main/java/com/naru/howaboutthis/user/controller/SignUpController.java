package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.user.domain.Policy;
import com.naru.howaboutthis.user.domain.PolicySingleton;
import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/users")
@RestController
public class SignUpController {

    private final UserRepository userRepository;

    // 생성자 기반 DI
    SignUpController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/policy")
    public Policy policy() {
        return PolicySingleton.getInstance();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
