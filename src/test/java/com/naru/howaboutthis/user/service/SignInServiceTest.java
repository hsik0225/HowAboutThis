package com.naru.howaboutthis.user.service;

import com.naru.howaboutthis.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SignInServiceTest {

    private User savedUser;

    @BeforeEach
    public void setup() {
        savedUser = User.builder()
                .email("naru@naver.com")
                .password("1234")
                .build();

        savedUser.hashPassword(savedUser);
        assertNotNull(savedUser);
    }

    @Test
    public void 유저_비밀번호_비교_성공() {
        User loginRequestUser = User.builder()
                .email("naru@naver.com")
                .password("1234")
                .build();

        assertNotNull(loginRequestUser);
        assertDoesNotThrow(() -> savedUser.checkPassword(loginRequestUser));
    }

    @Test
    public void 유저_비밀번호_비교_실패() {
        User loginRequestUser = User.builder()
                .email("naru@naver.com")
                .password("1235")
                .build();

        assertNotNull(loginRequestUser);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> savedUser.checkPassword(loginRequestUser)
        );

        assertThat(exception.getMessage()).isEqualTo("존재하지 않는 아이디이거나 비밀번호가 틀립니다");
    }
}
