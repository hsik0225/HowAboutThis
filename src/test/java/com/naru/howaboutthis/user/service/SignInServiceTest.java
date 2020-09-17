package com.naru.howaboutthis.user.service;

import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.util.ExceptionHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SignInServiceTest {

    @Autowired
    private SignInService signInService;

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
    public void 존재하지_않는_이메일() {
        ExceptionHelper.exceptionTest(
                EntityNotFoundException.class,
                () -> signInService.checkUserByEmail("not@naru.com"),
                "이 이메일로 가입된 아이디가 존재하지 않습니다"
        );
    }

    @Test
    public void 존재하지_않는_유저_조회_예외처리() {
        ExceptionHelper.exceptionTest(
                EntityNotFoundException.class,
                () -> signInService.findById(0L),
                "이 회원은 잘못된 회원입니다\n" +
                        "Id가 존재하지 않습니다"
        );
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
        ExceptionHelper.exceptionTest(
                IllegalArgumentException.class,
                () -> savedUser.checkPassword(loginRequestUser),
                "존재하지 않는 아이디이거나 비밀번호가 틀립니다"
        );
    }
}
