package com.example.demo.graphql.query;

import com.example.demo.domain.Beer;
import com.example.demo.repository.BeerRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BeerQueries implements GraphQLQueryResolver {

    private final BeerRepository beerRepository;

    public Beer beer(final Integer id) {
        return beerRepository
                .findById(id)
                .orElse(null);
    }

    public List<Beer> beers() {
        return beerRepository.findAll();
    }

}
