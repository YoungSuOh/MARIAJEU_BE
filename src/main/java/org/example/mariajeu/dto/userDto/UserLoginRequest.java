package org.example.mariajeu.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequest {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userName;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
