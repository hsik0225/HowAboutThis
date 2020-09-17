package com.naru.howaboutthis.user.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OAuthController.class)
class OAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("OAuth_로그인_테스트")
    public void OAuth_로그인_테스트() throws Exception {
        mockMvc.perform(get("/oauth/code"))
                .andDo(print())
                .andExpect(status().isSeeOther());
    }

    @Test
    @DisplayName("OAuth_로그인_리다이렉트_테스트")
    public void OAuth_로그인_리다이렉트_테스트() throws Exception {
        mockMvc.perform(get("/oauth").param("code", "OauthCode"))
                .andDo(print())
                .andExpect(status().isFound());
    }
}