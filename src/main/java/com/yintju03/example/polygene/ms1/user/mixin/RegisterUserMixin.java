package com.yintju03.example.polygene.ms1.user.mixin;

import com.yintju03.example.polygene.ms1.user.RegisterUser;
import com.yintju03.example.polygene.ms1.user.entity.UserEntity;
import com.yintju03.example.polygene.ms1.user.exception.PasswordNoMatchedException;
import com.yintju03.example.polygene.ms1.user.exception.UserAlreadyExistsException;
import com.yintju03.example.polygene.ms1.user.exception.UserNotSavedException;
import com.yintju03.example.polygene.ms1.user.factory.UserEntityFactory;
import com.yintju03.example.polygene.ms1.user.repository.UserEntityRepository;
import com.yintju03.example.polygene.ms1.user.value.UserRegisterInfoValue;

import org.apache.polygene.api.injection.scope.Service;
import org.apache.polygene.api.injection.scope.Structure;
import org.apache.polygene.api.structure.Module;
import org.apache.polygene.api.value.ValueBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterUserMixin implements RegisterUser {
    private static final Logger logger = LoggerFactory.getLogger(RegisterUserMixin.class);

    @Structure
    private Module module;

    @Service
    private UserEntityRepository userRepository;

    @Service
    private UserEntityFactory userFactory;

    @Override
    public void register(String name, String password, String confirmedPassword) throws UserAlreadyExistsException, PasswordNoMatchedException, UserNotSavedException {
        logger.info("register, name = {}, password = {}, confirmedPassword = {}", name, password, confirmedPassword);
        UserRegisterInfoValue uri = makeRegisterInfo(name, password, confirmedPassword);
        check(uri);
        createAndSave(uri);
    }

    private void check(UserRegisterInfoValue uri) throws PasswordNoMatchedException, UserAlreadyExistsException {
        String name = uri.name().get(), password = uri.password().get(), confirmedPassword = uri.confirmedPassword().get();
        UserEntity user = userRepository.findByName(name);
        if(user != null)
            throw new UserAlreadyExistsException();
        if(!password.equals(confirmedPassword))
            throw new PasswordNoMatchedException();
    }

    private void createAndSave(UserRegisterInfoValue uri) throws UserNotSavedException {
        UserEntity user = userFactory.create(uri);
        if(!userRepository.save(user))
            throw new UserNotSavedException();
    }

    private UserRegisterInfoValue makeRegisterInfo(String name, String password, String confirmedPassword) {
        ValueBuilder<UserRegisterInfoValue> builder = module.newValueBuilder(UserRegisterInfoValue.class);
        UserRegisterInfoValue prototype = builder.prototype();
        prototype.name().set(name);
        prototype.password().set(password);
        prototype.confirmedPassword().set(confirmedPassword);
        return builder.newInstance();
    }
    
}
