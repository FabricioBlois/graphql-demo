package com.example.demo.config;

import ma.glasnost.orika.metadata.ClassMapBuilder;

/**
 * Implement Mapping by implanting this interface
 */
public interface ObjectMapping<A, B> {

    void classMap(ClassMapBuilder<A, B> classMapBuilder);

}
