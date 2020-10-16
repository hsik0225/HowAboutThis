package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SignUpValidationTest {

    @Autowired
    protected SignUpController signUpController;

    @Autowired
    protected MockMvc mockMvc;

    protected User testUser;

    @BeforeEach
    public void setup() {
        testUser = User.getTestUser();
    }

    @DisplayName("비어있는_비밀번호")
    @Test
    public void 비어있는_비밀번호() {

    }

    @DisplayName("16자보다_긴_비밀번호")
    @Test
    public void 양식보다_긴_비밀번호() {

    }

    @DisplayName("16자보다_짧은_비밀번호")
    @Test
    public void 양식보다_짧은_비밀번호() {

    }

    @DisplayName("비어있는_이름")
    @Test
    public void 비어있는_이름() {

    }

    @DisplayName("16자보다_긴_이름")
    @Test
    public void 양식보다_긴_이름() {

    }

    @DisplayName("16자보다_짧은_이름")
    @Test
    public void 양식보다_짧은_이름() {

    }

    @DisplayName("대문자만_들어간_비밀번호")
    @Test
    public void 대문자만_들어간_비밀번호() {

    }

    @DisplayName("소문자만_들어간_비밀번호")
    @Test
    public void 소문자만_들어간_비밀번호() {

    }

    @DisplayName("숫자만_들어간_비밀번호")
    @Test
    public void 숫자만_들어간_비밀번호() {

    }
}
