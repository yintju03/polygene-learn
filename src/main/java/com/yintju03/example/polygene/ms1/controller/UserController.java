package com.yintju03.example.polygene.ms1.controller;

import com.yintju03.example.polygene.ms1.configuration.PolygeneConfiguration.FindService;
import com.yintju03.example.polygene.ms1.user.exception.PasswordNoMatchedException;
import com.yintju03.example.polygene.ms1.user.exception.UserAlreadyExistsException;
import com.yintju03.example.polygene.ms1.user.exception.UserNotSavedException;
import com.yintju03.example.polygene.ms1.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FindService<Class<UserService>,UserService> findService;

    @RequestMapping("/register/{name}/{password}/{confirmedPassword}")
    public void register(@PathVariable("name")String name,
                        @PathVariable("password")String password,
                        @PathVariable("confirmedPassword")String confirmedPassword) {
        try {
            findService.find(UserService.class).register(name, password, confirmedPassword);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PasswordNoMatchedException e) {
            e.printStackTrace();
        } catch (UserNotSavedException e) {
            e.printStackTrace();
        }
    }
}
