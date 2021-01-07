package com.example.demo.config;

import ma.glasnost.orika.Mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this is an Markup annotation.
 * {@link ma.glasnost.orika.MapperFacade} holds the mapping aginst
 * Pair of {@link ma.glasnost.orika.metadata.Type} A and B,
 * and it gets overridden with {@link ma.glasnost.orika.CustomMapper}
 * <p>
 * Annotate All the implementation of {@link ma.glasnost.orika.CustomMapper}
 * which needs to be registered with configuration
 * <p>
 * other {@link ma.glasnost.orika.CustomMapper} can be used as an Customizer
 * e.g {@link ma.glasnost.orika.metadata.ClassMapBuilder#customize(Mapper)}
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalMapper {

}
