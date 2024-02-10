package org.example.mariajeu.dto.userDto;

import org.example.mariajeu.domain.userDomain.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserListResponse {
    private String userName;
    private String name;
    private String email;
    private String nickName;
    private Role role;



}
