package com.naru.howaboutthis.user.domain;

import com.naru.howaboutthis.user.controller.SignIn;
import com.naru.howaboutthis.user.controller.SignUp;
import lombok.*;

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
    @Size(min = 8, max = 16)

    // 대/소 영문자, 숫자가 적어도 한 번은 포함되어야 한다
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]*$")
    private String password;

    @Column(name = "name")
    @Null(groups = SignIn.class)
    @NotEmpty(groups = SignUp.class)
    @Size(min = 2, max = 16, groups = SignUp.class)
    private String name;
}
