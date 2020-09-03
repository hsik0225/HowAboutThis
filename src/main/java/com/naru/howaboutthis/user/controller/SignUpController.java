package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.user.domain.Policy;
import com.naru.howaboutthis.user.domain.PolicyFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class SignUpController {

    @GetMapping("/policy")
    public Policy policy() {
        return PolicyFactory.getInstance();
    }

}
