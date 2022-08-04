package com.cloud.spring.microservices.circuitbreakerdemo.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Component
public class CircuitBreakerConfiguration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CircuitBreakerConfiguration.class);

    @Bean
    public CircuitBreaker countCircuitBreaker()
    {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                //If slidingWindowType is COUNT_BASED, the last slidingWindowSize calls are recorded and aggregated.
                .slidingWindowSize(3)
                .slowCallRateThreshold(65.0f)
                .slowCallDurationThreshold(Duration.ofSeconds(3))
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry =
                CircuitBreakerRegistry.of(circuitBreakerConfig);

        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("BooksSearchServiceBasedOnCount");

        return cb;
    }

    @Bean
    public CircuitBreaker timeCircuitBreaker()
    {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
    //the minimum number of calls that must be recorded before the failure rate can be calculated.
    //For example, if minimumNumberOfCalls is 10, then at least 10 calls must be recorded, before the failure rate can be calculated.
     // If only 9 calls have been recorded, the CircuitBreaker will not transition to open, even if all 9 calls have failed.
                .minimumNumberOfCalls(10)

                .slidingWindowSize(10)
    //Configures the failure rate threshold in percentage. If the failure rate is equal to or greater than the threshold
    // the CircuitBreaker transitions to open and starts short-circuiting calls.
                .failureRateThreshold(70.0f)
    //the wait duration which specifies how long the CircuitBreaker should stay open
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry =
                CircuitBreakerRegistry.of(circuitBreakerConfig);

        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("BookSearchServiceBasedOnTime");
        return cb;
    }
}
