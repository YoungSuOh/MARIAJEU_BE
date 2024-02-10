package org.example.mariajeu.service.userService;

import org.example.mariajeu.domain.userDomain.Terms;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.userDto.UserJoinRequest;
import org.example.mariajeu.dto.userDto.UserListResponse;
import org.example.mariajeu.dto.userDto.UserModifyRequest;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.repository.userRepository.JwtRepository;
import org.example.mariajeu.repository.userRepository.TermsRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TermsRepository termsRepository;
    private final JwtRepository jwtRepository;
    private final BCryptPasswordEncoder encoder;

    private final EntityManager em;

//    @Value("${jwt.token.secret}")
//    private String key;
//    private Long expireTimeMs = 60 * 60 * 1000L;
//    private Long RefreshTokenexpireTimeMs = 24 * 60 * 60 * 1000L;


    @Override
    public void join(UserJoinRequest dto){
        userRepository.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.BAD_REQUEST, "USERNAME : " + dto.getUserName() + "는 이미 있습니다");
                });
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.BAD_REQUEST,dto.getEmail() + "은 이미 사용중인 이메일입니다.");
                });
        userRepository.findByNickName(dto.getNickName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.BAD_REQUEST, dto.getNickName() + "은 이미 사용중인 닉네임입니다.");
                });


        User user = User.builder()
                .userName(dto.getUserName())
                .password(encoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .nickName(dto.getNickName())
                .name(dto.getName())
                .role(dto.getRole())
                .build();
        User savedUser = userRepository.save(user);

        Terms term = Terms.builder()
                .agreedToTerms1(dto.isAgreedToTerms1())
                .agreedToTerms2(dto.isAgreedToTerms2())
                .agreedToOptionalTerms(dto.isAgreedToOptionalTerms())
                .user(savedUser)
                .build();
        termsRepository.save(term);

    }

    @Override
    public void modifyUser(String userName, UserModifyRequest dto){
        Optional<User> selectedUser = userRepository.findByUserName(userName);
        User user = selectedUser.orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if(dto.getNewPassword() != null) {
            if(encoder.matches(dto.getNewPassword(),user.getPassword())) {
                throw new AppException(ErrorCode.CONFLICT, "이미 같은 비밀번호로 설정되어 있습니다.");
            }

            user.setPassword(encoder.encode(dto.getNewPassword()));
        }
        if (dto.getNickName() != null) {
            if (user.getNickName().equals(dto.getNickName())) {
                throw new AppException(ErrorCode.CONFLICT, "이미 같은 닉네임으로 설정되어 있습니다.");
            }
            userRepository.findByNickName(dto.getNickName()).ifPresent(user1 -> {
                throw new AppException(ErrorCode.CONFLICT, "이미 같은 닉네임이 존재합니다");
            });
            user.setNickName(dto.getNickName());
        }

        if(dto.getEmail() != null){
            if(user.getEmail().equals(dto.getEmail())) {
                throw new AppException(ErrorCode.CONFLICT, "이미 같은 Email로 설정되어 있습니다.");
            }
            userRepository.findByEmail(dto.getEmail()).ifPresent(user1 -> {
                throw new AppException(ErrorCode.CONFLICT, "이미 같은 Email이 존재합니다");
            });
            user.setEmail(dto.getEmail());
        }

        if (dto.getRole() != null) {
            if (user.getRole().equals(dto.getRole())) {
                throw new AppException(ErrorCode.CONFLICT, "이미 같은 권한을 소지하고 있습니다.");
            }
            user.setRole(dto.getRole());
        }

        if (dto.getName() != null) {
            if (user.getName().equals(dto.getName())) {
                throw new AppException(ErrorCode.CONFLICT, "이미 같은 이름으로 설정되어 있습니다.");
            }
            user.setName(dto.getName());
        }

        userRepository.save(user);
    }

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
    public UserListResponse getUser(String userName){

        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, "해당" +userName + "가 없습니다."));

        UserListResponse userListResponse = UserListResponse.builder()
                .userName(selectedUser.getUserName())
                .name(selectedUser.getName())
                .nickName(selectedUser.getNickName())
                .email(selectedUser.getEmail())
                .role(selectedUser.getRole())
                .build();

        return  userListResponse;
    }

    @Override
    public List<UserListResponse> getALL() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String userName){

        User user = userRepository.findByUsername(userName);
        Terms terms = user.getTerms();
        termsRepository.delete(terms);
        em.flush();
        em.clear();
        userRepository.delete(user);
    }







}
