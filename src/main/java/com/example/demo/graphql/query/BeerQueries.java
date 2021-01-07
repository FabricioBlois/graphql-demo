package com.example.demo.graphql.query;

import com.example.demo.graphql.response.Beer;
import com.example.demo.repository.BeerRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BeerQueries implements GraphQLQueryResolver {

    private final BeerRepository beerRepository;
    private final MapperFacade mapperFacade;

    public Beer beer(final Integer id) {
        return beerRepository
                .findById(id)
                .map(beer -> mapperFacade.map(beer, Beer.class))
                .orElse(null);
    }

    public List<Beer> beers() {
        return beerRepository
                .findAll()
                .stream()
                .map(beer -> mapperFacade.map(beer, Beer.class))
                .collect(Collectors.toList());
    }

}
