package com.yintju03.example.polygene.ms1.user.service;

import com.yintju03.example.polygene.ms1.common.MyTimingCapture;
import com.yintju03.example.polygene.ms1.user.RegisterUser;
import com.yintju03.example.polygene.ms1.user.mixin.RegisterUserMixin;

import org.apache.polygene.api.concern.Concerns;
import org.apache.polygene.api.identity.IdentityGenerator;
import org.apache.polygene.api.identity.UuidGeneratorMixin;
import org.apache.polygene.api.metrics.MetricsProvider;
import org.apache.polygene.api.mixin.Mixins;
import org.apache.polygene.spi.metrics.MetricsProviderAdapter;

@Concerns(MyTimingCapture.class)
@Mixins({UuidGeneratorMixin.class, MetricsProviderAdapter.class, RegisterUserMixin.class})
public interface UserService extends IdentityGenerator, MetricsProvider, RegisterUser {
    
}
