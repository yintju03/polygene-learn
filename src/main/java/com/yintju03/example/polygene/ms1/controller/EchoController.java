package com.yintju03.example.polygene.ms1.controller;

import com.yintju03.example.polygene.ms1.configuration.PolygeneConfiguration.FindService;
import com.yintju03.example.polygene.ms1.echo.service.EchoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/echo")
public class EchoController {
    @Autowired
    private FindService<Class<EchoService>,EchoService> findService;

    @RequestMapping("/{hello}")
    public String echo(@PathVariable("hello") String hello) {
        return findService.find(EchoService.class).echo(hello);
    }
}
