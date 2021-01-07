package com.example.demo.graphql.instrumentation;

import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomInstrumentation {

    @Bean
    public Instrumentation tracing() {
        return new TracingInstrumentation();
    }

}
