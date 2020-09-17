package com.naru.howaboutthis.user.service;

import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class SignInService {

    private final UserRepository userRepository;

    public void signIn(User loginRequestUser) {
        String email = loginRequestUser.getEmail();
        checkUserByEmail(email);

        User savedUser = userRepository.findById(loginRequestUser.getId()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 아이디입니다"));
        savedUser.checkPassword(loginRequestUser);
    }

    public void checkUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new EntityNotFoundException("이 이메일로 가입된 아이디가 존재하지 않습니다");
        }
    }
}
