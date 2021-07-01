package com.yintju03.example.polygene.ms1.user;

import com.yintju03.example.polygene.ms1.user.exception.PasswordNoMatchedException;
import com.yintju03.example.polygene.ms1.user.exception.UserAlreadyExistsException;
import com.yintju03.example.polygene.ms1.user.exception.UserNotSavedException;

public interface RegisterUser {
    void register(String name, String password, String confirmedPassword) throws UserAlreadyExistsException, PasswordNoMatchedException, UserNotSavedException;
}
