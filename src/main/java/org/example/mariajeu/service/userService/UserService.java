package org.example.mariajeu.service.userService;

import org.example.mariajeu.dto.userDto.UserJoinRequest;
import org.example.mariajeu.dto.userDto.UserListResponse;
import org.example.mariajeu.dto.userDto.UserModifyRequest;

import java.util.List;


public interface UserService {


    void join(UserJoinRequest dto);

    //TokenDto login(String userName, String password);

    void modifyUser(String userName, UserModifyRequest dto);

    Boolean CheckPassword(String selectedUserName, String selectedUserPassword);

    UserListResponse getUser(String userName);

    List<UserListResponse> getALL();

    void deleteUser(String userName);

}