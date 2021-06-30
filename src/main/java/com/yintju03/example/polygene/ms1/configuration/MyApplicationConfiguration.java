package com.yintju03.example.polygene.ms1.configuration;

import com.yintju03.example.polygene.ms1.annotation.InLayer;
import com.yintju03.example.polygene.ms1.configuration.PolygeneConfiguration.CreateLayer;
import com.yintju03.example.polygene.ms1.configuration.PolygeneConfiguration.CreateModule;
import com.yintju03.example.polygene.ms1.echo.service.EchoService;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.polygene.api.common.Visibility;
import org.apache.polygene.bootstrap.ModuleAssembly;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Configuration
public class MyApplicationConfiguration {
    @Bean
    public CreateLayer domainLayer(String[] use) {
        return app -> ImmutablePair.of(app.layer("domain-layer"), use);
    }

    @Bean
    @InLayer("domainLayer")
    public CreateModule echoModule(@Nullable String layer) {
        return new CreateModule(layer, v -> {
            ModuleAssembly module = v.module("echo-module");
            module.addServices(EchoService.class)
                .visibleIn(Visibility.layer);
            return module;
        });
    }
}
