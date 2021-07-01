package com.yintju03.example.polygene.ms1.configuration;

import com.yintju03.example.polygene.ms1.annotation.InLayer;
import com.yintju03.example.polygene.ms1.configuration.PolygeneConfiguration.CreateLayer;
import com.yintju03.example.polygene.ms1.configuration.PolygeneConfiguration.CreateModule;
import com.yintju03.example.polygene.ms1.echo.service.EchoService;
import com.yintju03.example.polygene.ms1.user.entity.UserEntityImpl;
import com.yintju03.example.polygene.ms1.user.service.UserEntityFactoryService;
import com.yintju03.example.polygene.ms1.user.service.UserEntityRepositoryService;
import com.yintju03.example.polygene.ms1.user.service.UserService;
import com.yintju03.example.polygene.ms1.user.value.UserRegisterInfoValueImpl;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.polygene.api.common.Visibility;
import org.apache.polygene.bootstrap.ModuleAssembly;
import org.apache.polygene.entitystore.memory.assembly.MemoryEntityStoreAssembler;
import org.apache.polygene.serialization.javaxjson.assembly.JavaxJsonSerializationAssembler;
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

    @Bean
    @InLayer("domainLayer")
    public CreateModule userModule(@Nullable String layer) {
        return new CreateModule(layer, v -> {
            ModuleAssembly module = v.module("user-module");
            new JavaxJsonSerializationAssembler().assemble(module);
            new MemoryEntityStoreAssembler().assemble(module);
            module.addServices(UserService.class)
                .visibleIn(Visibility.layer);
            module.addServices(UserEntityRepositoryService.class,
                UserEntityFactoryService.class);
            module.values(UserRegisterInfoValueImpl.class);
            module.entities(UserEntityImpl.class);
            return module;
        });
    }

}
