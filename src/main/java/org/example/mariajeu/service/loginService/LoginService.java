package org.example.mariajeu.service.loginService;

import org.example.mariajeu.dto.userDto.TokenDto;

public interface LoginService {
    TokenDto login(String userName, String password);
}
