package com.cloud.spring.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                // if a request starts with /currency-exchange/** then find name of the registration on the eureka server
                // find the location of the server and load balances between the instances
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))


                .route(p -> p.path("/currency-converter/**")
                        .uri("lb://currency-converter"))
                .route(p -> p.path("/currency-converter-feign/**")
                        .uri("lb://currency-converter"))

                .build();
    }

}