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
        savedUser = User.getTestUser();
        savedUser.hashPassword(savedUser);
        assertNotNull(savedUser);
    }

    @Test
    public void 존재하지_않는_유저_조회_예외처리() {
        ExceptionHelper.exceptionTest(
                EntityNotFoundException.class,
                () -> signInService.findByEmail("not@naru.com"),
                "이 이메일로 가입된 아이디가 존재하지 않습니다"
        );
    }

    @Test
    public void 유저_비밀번호_비교_성공() {
        User loginRequestUser = User.getTestUser();
        assertDoesNotThrow(() -> savedUser.checkPassword(loginRequestUser));
    }

    @Test
    public void 유저_비밀번호_비교_실패() {
        User loginRequestUser = User.getTestUser();
        loginRequestUser.setPassword("testA1");
        ExceptionHelper.exceptionTest(
                IllegalArgumentException.class,
                () -> savedUser.checkPassword(loginRequestUser),
                "존재하지 않는 아이디이거나 비밀번호가 틀립니다"
        );
    }
}
