package com.naru.howaboutthis.user.service;

import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/*
@DataJpaTest를 사용하고 싶었으나 실제 DB를 사용하기 위해 DB의 정보를
복호화시켜주는 JasyptEncryptor가 필요하므로 @SpringBootTest를 사용하였다
 */
@SpringBootTest
class SignUpServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void 회원가입_테스트() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("q1w2e3r41!")
                .name("naru")
                .build();

        user.hashPassword(user);
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getEmail()).isEqualTo("test@gmail.com");
        assertThat(savedUser.getPassword()).isNotEqualTo("q1w2e3r41");
        assertThat(savedUser.getName()).isEqualTo("naru");
    }

    @Test
    @Transactional
    public void 중복_이메일_체크_테스트() {
        회원가입_테스트();
        boolean exists = userRepository.existsByEmail("test@gmail.com");
        assertThat(exists).isEqualTo(true);
    }
}