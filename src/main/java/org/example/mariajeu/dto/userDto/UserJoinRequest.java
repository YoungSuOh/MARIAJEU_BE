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
public class UserJoinRequest {
    private String userName;
    private String password;
    private String name;
    private String email;
    private String authNum;
    private String nickName;
    private Role role;
    private boolean agreedToTerms1;
    private boolean agreedToTerms2;
    private boolean agreedToOptionalTerms;
}
