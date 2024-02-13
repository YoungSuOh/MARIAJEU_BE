package org.example.mariajeu.controller.userController;

import jakarta.validation.Valid;
import org.example.mariajeu.domain.userDomain.Role;
import org.example.mariajeu.dto.userDto.*;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.logoutService.LogoutService;
import org.example.mariajeu.service.mailService.MailService;
import org.example.mariajeu.service.passwordService.PasswordService;
import org.example.mariajeu.service.userService.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final LogoutService logoutService;
    private final MailService mailService;
    private final PasswordService passwordService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody UserJoinRequest dto) {
        if (!dto.isAgreedToTerms1() || !dto.isAgreedToTerms2())
            throw new AppException(ErrorCode.BAD_REQUEST, "이용 약관의 필수 항목을 체크해주세요.");


        boolean isAuthVerified = mailService.CheckAuthNum(dto.getEmail(), dto.getAuthNum());

        if (!isAuthVerified)
            throw new AppException(ErrorCode.BAD_REQUEST, "Email verification failed.");

        userService.join(dto);
        mailService.DeleteAuthNum(dto.getAuthNum());

    return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modify(@Valid @RequestBody UserModifyRequest dto, Authentication authentication) {
        String userName = authentication.getName();
        passwordService.CheckPassword(userName,dto.getPassword());
        userService.modifyUser(userName,dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(name = "search") String target, Authentication authentication){
        // all 모두 (관리자권한만) //유저이름 유저데이터 반환 //빈칸이거나 공백이면 오류반환
        String reqUser = authentication.getName();
        UserListResponse user = userService.getUser(reqUser);
        if(target.equals("All")) {
            if (user.getRole().equals(Role.ADMIN))
                return ResponseEntity.ok().body(userService.getALL());
            else
                throw new AppException(ErrorCode.BAD_REQUEST, "you don't currently have permission to access this.");
        }
        else{
            UserListResponse respUser = userService.getUser(target);
            return ResponseEntity.ok().body(respUser);
        }
    }

    @DeleteMapping("deleteUser/{userName}")
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userName") String userName, Authentication authentication, HttpServletRequest request) {
        String reqUser = authentication.getName();
        Role targetRole = userService.getUser(reqUser).getRole();

        if (targetRole.equals(Role.ADMIN) || reqUser.equals(userName)) {
            userService.deleteUser(userName);
            logoutService.logout(request);

            return ResponseEntity.ok(UserDeleteResponse.builder()
                    .status(HttpStatus.OK)
                    .userName(userName)
                    .build()
            );
        }
        else
            throw new AppException(ErrorCode.BAD_REQUEST, "해당 유저를 삭제할 권한이 없습니다.");
    }


}
