package com.cloud.spring.microservices.limitservice.controller;

import com.cloud.spring.microservices.limitservice.configuration.Configuration;
import com.cloud.spring.microservices.limitservice.model.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        return new LimitConfiguration(configuration.getMaximum(),
                configuration.getMinimum());
    }

}