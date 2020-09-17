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
        checkUserByEmail(email);

        User savedUser = findById(loginRequestUser.getId());
        savedUser.checkPassword(loginRequestUser);
    }

    public void checkUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new EntityNotFoundException("이 이메일로 가입된 아이디가 존재하지 않습니다");
        }
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "이 회원은 잘못된 회원입니다\n"
                                + "Id가 존재하지 않습니다"
                ));
    }
}
