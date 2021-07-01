package com.yintju03.example.polygene.ms1.user.repository;

import com.yintju03.example.polygene.ms1.user.entity.UserEntity;

public interface UserEntityRepository {
    UserEntity findByName(String name);
    boolean save(UserEntity entity);
}
