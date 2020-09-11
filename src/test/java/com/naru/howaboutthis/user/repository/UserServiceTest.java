package com.naru.howaboutthis.user.repository;

import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeAll
    public void setUp() {
        user = signUp();
    }

    private User signUp() {
        return userRepository.save(
                User.builder()
                        .name("naru")
                        .email("test@naver.com")
                        .password("test1234")
                        .build()
        );
    }

    @Test
    @DisplayName("회원가입_테스트")
    public void 회원가입_테스트() {
        if (user == null) user = signUp();
        assertThat(user.getName()).isEqualTo("naru");
        assertThat(user.getEmail()).isEqualTo("test@naver.com");
        assertThat(user.getPassword()).isEqualTo("test1234");
    }

    @Test
    @DisplayName("단일_조회_테스트")
    void 단일_조회_테스트() {
        // given
        String email = "test@naver.com";

        // when
        User foundUser = userRepository.findByEmail(email).get();

        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("naru");
        assertThat(foundUser.getEmail()).isEqualTo("test@naver.com");
        assertThat(foundUser.getPassword()).isEqualTo("test1234");
    }
}
