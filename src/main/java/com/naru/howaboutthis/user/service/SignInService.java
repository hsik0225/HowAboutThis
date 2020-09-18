package com.naru.howaboutthis.user.service;

import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class SignInService {

    private final UserRepository userRepository;

    @Transactional
    public void signIn(User loginRequestUser) {
        String email = loginRequestUser.getEmail();
        User savedUser = findByEmail(email);
        savedUser.checkPassword(loginRequestUser);
    }

    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "이 이메일로 가입된 아이디가 존재하지 않습니다"
                ));
    }
}
