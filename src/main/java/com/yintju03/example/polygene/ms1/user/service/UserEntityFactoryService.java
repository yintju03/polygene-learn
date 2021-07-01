package com.yintju03.example.polygene.ms1.user.service;

import com.yintju03.example.polygene.ms1.common.MyTimingCapture;
import com.yintju03.example.polygene.ms1.user.factory.UserEntityFactory;
import com.yintju03.example.polygene.ms1.user.mixin.UserEntityFactoryMixin;

import org.apache.polygene.api.concern.Concerns;
import org.apache.polygene.api.mixin.Mixins;

@Concerns(MyTimingCapture.class)
@Mixins(UserEntityFactoryMixin.class)
public interface UserEntityFactoryService extends UserEntityFactory {
    
}
