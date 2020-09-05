package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.user.service.SignupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignupController.class)
class SignupControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SignupService signupService;

    @Test
    @DisplayName("이용약관 목록 테스트")
    void 이용약관_목록_테스트() throws Exception {
        mockMvc.perform(get("/api/users/policy"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length").exists())
                .andExpect(jsonPath("$.terms").exists());
    }

    @Test
    @DisplayName("중복되지_않은_이메일_입력_테스트")
    void 중복되지_않은_이메일_입력_테스트() throws Exception {
        String testEmail = "hrifle@gmail.com";
        mockMvc.perform(get("/api/users/{email}", testEmail))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("중복된_이메일_입력_테스트")
    void 중복된_이메일_입력_테스트() throws Exception {
        String testEmail = "hsik0225@gmail.com";
        mockMvc.perform(get("/api/users/{email}", testEmail))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @Transactional
    @DisplayName("회원가입_테스트")
    void 회원가입_테스트() {

    }
}