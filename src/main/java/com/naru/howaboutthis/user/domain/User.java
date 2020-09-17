package com.naru.howaboutthis.user.domain;

import com.naru.howaboutthis.user.controller.SignIn;
import com.naru.howaboutthis.user.controller.SignUp;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 8, max = 16, groups = SignUp.class)

    // 대/소 영문자, 숫자가 적어도 한 번은 포함되어야 한다
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]*$", groups = SignUp.class)
    private String password;

    @Column(name = "name")
    @Null(groups = SignIn.class)
    @NotEmpty(groups = SignUp.class)
    @Size(min = 2, max = 16, groups = SignUp.class)
    private String name;

    public void hashPassword(User user) {
        this.password = hashPassword(user.password);
    }

    public void checkPassword(User loginRequestUser) {
        String hashPassword = hashPassword(loginRequestUser.password);
        if (!this.name.equals(loginRequestUser.name)
                || !this.password.equals(hashPassword)) {
            throw new IllegalArgumentException("존재하지 않는 아이디이거나 비밀번호가 틀립니다");
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
