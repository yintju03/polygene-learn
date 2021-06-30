package com.yintju03.example.polygene.ms1.echo.mixin;

import com.yintju03.example.polygene.ms1.echo.Echo;

public class EchoMixin implements Echo {

    @Override
    public String echo(String hello) {
        return hello;
    }
    
}
