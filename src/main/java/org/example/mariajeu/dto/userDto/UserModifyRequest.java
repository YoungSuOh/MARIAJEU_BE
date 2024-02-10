package org.example.mariajeu.dto.userDto;

import org.example.mariajeu.domain.userDomain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModifyRequest {
    private String password;
    private String newPassword;
    private String name;
    private String email;
    private String nickName;
    private Role role;
}
