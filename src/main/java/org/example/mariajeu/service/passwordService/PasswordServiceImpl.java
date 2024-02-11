package org.example.mariajeu.service.passwordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PasswordServiceImpl implements PasswordService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    public Boolean CheckPassword(String selectedUserName, String selectedUserPassword){
        Optional<User> selectedUser = userRepository.findByUserName(selectedUserName);

        if (selectedUser.isPresent()) { //찾는 유저가 있으면
            User user = selectedUser.get();
            if(!encoder.matches(selectedUserPassword,user.getPassword())){
                return false;
            }
        }
        else { //찾는 유저가 없으면
            return false;
        }

        return true;
    }
    @Override
    public String findID(String email) {
        Optional<User> targetUser = userRepository.findByEmail(email);

        if (targetUser.isPresent()) { //찾는 유저가 있으면
            User user = targetUser.get();
            return user.getUserName();
        }
        else { //찾는 유저가 없으면
            throw new AppException(ErrorCode.NOT_FOUND,"해당 Email로 가입된 유저가 없습니다.");
        }
    }

    @Override
    public void findPWD(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, "해당" +userName + "가 없습니다."));
    }

}
