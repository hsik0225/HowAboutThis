package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.util.ExceptionHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

public class SignUpEmailValidationTest extends SignUpValidationTest {

    @DisplayName("형식에_맞지_않는_이메일")
    @Test
    public void 형식에_맞지_않는_이메일() {
        testUser.setEmail("test!naru.com");
        ExceptionHelper.exceptionTest(
                ConstraintViolationException.class,
                () -> signUpController.signUp(testUser),
                "email의 값을 잘못 입력하셨습니다"
        );
    }
}
