package com.naru.howaboutthis.user.repository;

import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = userRepository.save(
                User.builder()
                .name("naru")
                .email("test@naver.com")
                .password("test1234")
                .build()
        );
    }

    @Test
    public void 회원가입_테스트() {
        // then
        assertThat(testUser.getName()).isEqualTo("naru");
        assertThat(testUser.getEmail()).isEqualTo("test@naver.com");
        assertThat(testUser.getPassword()).isEqualTo("test1234");
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
    }
}
