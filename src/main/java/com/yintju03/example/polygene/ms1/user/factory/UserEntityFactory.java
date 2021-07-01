package com.yintju03.example.polygene.ms1.user.factory;

import com.yintju03.example.polygene.ms1.user.entity.UserEntity;
import com.yintju03.example.polygene.ms1.user.value.UserRegisterInfoValue;

public interface UserEntityFactory {
    UserEntity create(UserRegisterInfoValue uri);
}
