package com.yintju03.example.polygene.ms1.user.mixin;

import com.yintju03.example.polygene.ms1.user.entity.UserEntity;
import com.yintju03.example.polygene.ms1.user.factory.UserEntityFactory;
import com.yintju03.example.polygene.ms1.user.value.UserRegisterInfoValue;

import org.apache.polygene.api.entity.EntityBuilder;
import org.apache.polygene.api.injection.scope.Structure;
import org.apache.polygene.api.unitofwork.UnitOfWork;
import org.apache.polygene.api.unitofwork.UnitOfWorkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserEntityFactoryMixin implements UserEntityFactory {
    private static final Logger logger = LoggerFactory.getLogger(UserEntityFactoryMixin.class);

    @Structure
    private UnitOfWorkFactory uowf;

    @Override
    public UserEntity create(UserRegisterInfoValue uri) {
        String name = uri.name().get(), password = uri.password().get();
        logger.info("create, name = {}, password = {}", name, password);
        UnitOfWork uow = uowf.isUnitOfWorkActive() ? uowf.currentUnitOfWork() : uowf.newUnitOfWork();
        EntityBuilder<UserEntity> builder = uow.newEntityBuilder(UserEntity.class);
        UserEntity prototype = builder.instance();
        prototype.name().set(name);
        prototype.password().set(password);
        return builder.newInstance();
    }
    
}
