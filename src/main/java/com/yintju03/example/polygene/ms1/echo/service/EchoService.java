package com.yintju03.example.polygene.ms1.echo.service;

import com.yintju03.example.polygene.ms1.common.MyTimingCapture;
import com.yintju03.example.polygene.ms1.echo.Echo;
import com.yintju03.example.polygene.ms1.echo.activator.EchoActivator;
import com.yintju03.example.polygene.ms1.echo.mixin.EchoMixin;

import org.apache.polygene.api.activation.Activators;
import org.apache.polygene.api.concern.Concerns;
import org.apache.polygene.api.mixin.Mixins;

@Concerns(MyTimingCapture.class)
@Activators(EchoActivator.class)
@Mixins(EchoMixin.class)
public interface EchoService extends Echo {
    
}
