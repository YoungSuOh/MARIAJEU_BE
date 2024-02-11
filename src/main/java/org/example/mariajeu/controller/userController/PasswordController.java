package org.example.mariajeu.controller.userController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mariajeu.dto.userDto.EmailRequest;
import org.example.mariajeu.dto.userDto.UserModifyRequest;
import org.example.mariajeu.dto.userDto.UserPwdChangeRequest;
import org.example.mariajeu.dto.userDto.UserResponse;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.MailService.MailServiceImpl;
import org.example.mariajeu.service.passwordService.PasswordService;
import org.example.mariajeu.service.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/find")
public class PasswordController {
    private final PasswordService passwordService;
    private final UserService userService;
    private final MailServiceImpl mailService;

    @PostMapping("/id")
    public ResponseEntity<UserResponse> findID(@Valid @RequestBody EmailRequest dto) {
        String userName = passwordService.findID(dto.getEmail());

        UserResponse userResponse = UserResponse.builder()
                .status(HttpStatus.OK)
                .message(userName)
                .build();
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("/password")
    public ResponseEntity<UserResponse> findPWD(@Valid @RequestBody EmailRequest dto) {
        passwordService.findPWD(dto.getUserName());

        UserResponse userResponse = UserResponse.builder()
                .status(HttpStatus.OK)
                .message(mailService.joinEmail(dto.getEmail()))
                .build();

        return ResponseEntity.ok().body(userResponse);
    }

    @PatchMapping("/changePW")
    public ResponseEntity<?> changePWD(@Valid @RequestBody UserPwdChangeRequest dto) {
        String userName = passwordService.findID(dto.getEmail());

        if(!passwordService.CheckPassword(userName,dto.getPassword()))
            throw new AppException(ErrorCode.BAD_REQUEST, "입력하신 기존 비밀번호의 입력값이 서로 다릅니다 ");

        if(!dto.getNewPassword().equals(dto.getNewPasswordAgain()))
            throw new AppException(ErrorCode.BAD_REQUEST, "변경하려는 새 비밀번호의 입력값이 서로 다릅니다.");

        UserModifyRequest userModifyRequest = UserModifyRequest.builder()
                .newPassword(dto.getNewPassword())
                .build();

        userService.modifyUser(userName,userModifyRequest);

        return ResponseEntity.ok().body(userModifyRequest);
    }

}
