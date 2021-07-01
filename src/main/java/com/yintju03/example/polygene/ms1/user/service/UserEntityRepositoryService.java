package com.yintju03.example.polygene.ms1.user.service;

import com.yintju03.example.polygene.ms1.common.MyTimingCapture;
import com.yintju03.example.polygene.ms1.user.mixin.UserEntityRepositoryMixin;
import com.yintju03.example.polygene.ms1.user.repository.UserEntityRepository;

import org.apache.polygene.api.concern.Concerns;
import org.apache.polygene.api.mixin.Mixins;

@Concerns(MyTimingCapture.class)
@Mixins(UserEntityRepositoryMixin.class)
public interface UserEntityRepositoryService extends UserEntityRepository {
    
}
