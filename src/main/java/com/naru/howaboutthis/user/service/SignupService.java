package com.naru.howaboutthis.user.service;

import com.naru.howaboutthis.exception.DuplicateEmailException;
import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupService {

    private final UserRepository userRepository;

    // 생성자 기반 DI
    public SignupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // javax Transactional vs SpringFramework Transactional
    // http://wonwoo.ml/index.php/post/776
    @Transactional
    public void save(User user) {
        String email = user.getEmail();
        if (!canUseEmail(email)) {
            throw new DuplicateEmailException("중복된 이메일입니다");
        }
        userRepository.save(user);
    }

    public boolean canUseEmail(String email) {
        return userRepository.findByEmail(email) == null;
    }
}
