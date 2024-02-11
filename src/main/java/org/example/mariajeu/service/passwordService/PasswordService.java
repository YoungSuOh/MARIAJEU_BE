package org.example.mariajeu.service.passwordService;

public interface PasswordService {
    Boolean CheckPassword(String selectedUserName, String selectedUserPassword);
    String findID(String email);
    void findPWD(String userName);
}
