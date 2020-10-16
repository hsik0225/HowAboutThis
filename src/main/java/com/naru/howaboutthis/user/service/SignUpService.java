package com.naru.howaboutthis.user.service;

import com.naru.howaboutthis.exception.DuplicateEmailException;
import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final UserRepository userRepository;

    @Transactional
    public void save(User user) {
        String email = user.getEmail();
        checkDuplicateEmail(email);
        user.hashPassword(user);
        userRepository.save(user);
    }

    public void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("중복된 이메일입니다");
        }
    }
}
