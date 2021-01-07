package com.example.demo.mapping;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OptionalConverter extends BidirectionalConverter<Object, Optional<Object>> {

    @Override
    public boolean canConvert(Type<?> sourceType, Type<?> destinationType) {
        return (isRawTypeAssignableFromOptional(sourceType) && !isRawTypeAssignableFromOptional(destinationType))
                || (isRawTypeAssignableFromOptional(destinationType) && !isRawTypeAssignableFromOptional(sourceType));
    }

    @Override
    public Optional<Object> convertTo(Object source, Type<Optional<Object>> destinationType, MappingContext mappingContext) {
        return Optional.ofNullable(source);
    }

    @Override
    public Object convertFrom(Optional<Object> source, Type<Object> destinationType, MappingContext mappingContext) {
        return mapperFacade.map(source.orElse(null), destinationType.getRawType(), mappingContext);
    }

    private boolean isRawTypeAssignableFromOptional(Type<?> source) {
        return Optional.class.isAssignableFrom(source.getRawType());
    }

}
