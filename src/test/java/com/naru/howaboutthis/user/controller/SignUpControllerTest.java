package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.config.CharsetEncodingFilter;
import com.naru.howaboutthis.exception.DuplicateEmailException;
import com.naru.howaboutthis.exception.advice.UserExceptionAdvice;
import com.naru.howaboutthis.user.domain.Policy;
import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.service.SignUpService;
import com.naru.howaboutthis.util.ObjectToJsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SignUpControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Policy policy;

    @Mock
    private SignUpService signupService;

    @InjectMocks
    private SignUpController signUpController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(signUpController)
                .setControllerAdvice(new UserExceptionAdvice())
                .addFilters(new CharsetEncodingFilter())
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("이용약관 목록 테스트")
    void 이용약관_목록_테스트() throws Exception {
        String jsonPolicy = ObjectToJsonConverter.ObjectToJson(policy);
        mockMvc.perform(get("/api/users/policy"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonPolicy));
    }

    @Test
    @DisplayName("중복되지_않은_이메일_입력_테스트")
    void 중복되지_않은_이메일_입력_테스트() throws Exception {
        // given
        when(signupService.isDuplicated(anyString())).thenReturn(false);

        // when, then
        mockMvc.perform(get("/api/users/test@mail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @DisplayName("중복된_이메일_입력_테스트")
    void 중복된_이메일_입력_테스트() throws Exception {
        // given
        when(signupService.isDuplicated(anyString())).thenThrow(DuplicateEmailException.class);

        // when, then
        mockMvc.perform(get("/api/users/test@mail.com"))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("회원가입_테스트")
    void 회원가입_테스트() throws Exception {
        User testUser = new User();
        testUser.setEmail("test@email.com");
        testUser.setName("naru");
        testUser.setPassword("Test1234");

        String jsonTestUser = ObjectToJsonConverter.ObjectToJson(testUser);
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(jsonTestUser))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/api/main"));
    }
}