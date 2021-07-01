package com.yintju03.example.polygene.ms1.user.mixin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yintju03.example.polygene.ms1.user.entity.UserEntity;
import com.yintju03.example.polygene.ms1.user.repository.UserEntityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserEntityRepositoryMixin implements UserEntityRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserEntityRepositoryMixin.class);
    private static final Map<String,UserEntity> data = new ConcurrentHashMap<>();

    @Override
    public UserEntity findByName(String name) {
        logger.info("findByName, name = {}", name);
        return data.get(name);
    }

    @Override
    public boolean save(UserEntity entity) {
        logger.info("save, entity = {}", entity);
        return data.putIfAbsent(entity.name().get(), entity) == null;
    }
    
}
