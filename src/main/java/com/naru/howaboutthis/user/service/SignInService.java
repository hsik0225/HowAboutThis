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

    public void checkUserByEmail(User requestUser) {
        String email = requestUser.getEmail();
        boolean isSignedUpUser = userRepository.findByEmail(email).isPresent();
        if (!isSignedUpUser) {
            throw new EntityNotFoundException("이 이메일로 가입된 아이디가 존재하지 않습니다");
        }
    }
}
