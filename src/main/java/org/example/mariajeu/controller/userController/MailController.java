package org.example.mariajeu.controller.userController;

import org.example.mariajeu.dto.userDto.EmailResponse;
import org.example.mariajeu.dto.userDto.EmailCheck;
import org.example.mariajeu.dto.userDto.EmailRequest;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.MailService.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/Send")
    public ResponseEntity<EmailResponse> mailSend(@RequestBody @Valid EmailRequest dto) {
        System.out.println("이메일 인증 이메일 :" + dto.getEmail());

        EmailResponse emailResponse = EmailResponse.builder()
                .status(HttpStatus.OK)
                .message(mailService.joinEmail(dto.getEmail()))
                .build();


        return ResponseEntity.ok(emailResponse);
    }

    @PostMapping("/AuthCheck")
    public ResponseEntity<EmailResponse> AuthCheck(@RequestBody @Valid EmailCheck dto){
        Boolean Checked=mailService.CheckAuthNum(dto.getEmail(),dto.getAuthNum());

        EmailResponse emailResponse = EmailResponse.builder()
                .status(HttpStatus.OK)
                .message("인증이 완료되었습니다")
                .build();

        if(Checked){
            return ResponseEntity.ok(emailResponse);
        }
        else{
            throw new AppException(ErrorCode.BAD_REQUEST,"인증 번호가 맞지 않습니다.");
        }
    }

}