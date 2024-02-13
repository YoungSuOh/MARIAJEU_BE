package org.example.mariajeu.controller.userController;

import jakarta.validation.Valid;
import org.example.mariajeu.dto.userDto.TokenDto;
import org.example.mariajeu.dto.userDto.UserLoginRequest;
import org.example.mariajeu.dto.userDto.UserResponse;
import org.example.mariajeu.service.authService.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.mariajeu.service.loginService.LoginService;
import org.example.mariajeu.service.logoutService.LogoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final LoginService loginService;
    private final LogoutService logoutService;

    @GetMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        String refreshToken = authorization.split(" ")[1];
        String newAccessToken = authService.reissue(refreshToken);
        TokenDto tokenDto = new TokenDto(newAccessToken,null);
        return ResponseEntity.ok().body(tokenDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest dto){
        TokenDto token = loginService.login(dto.getUserName(), dto.getPassword());

        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/logout")
    public ResponseEntity<UserResponse> logoutSuccess(HttpServletRequest request) {
        return ResponseEntity.ok(logoutService.logout(request));
    }

}
