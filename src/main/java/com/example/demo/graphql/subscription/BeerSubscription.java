package com.example.demo.graphql.subscription;

import com.example.demo.graphql.response.Beer;
import com.example.demo.repository.BeerRepository;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BeerSubscription implements GraphQLSubscriptionResolver {

    private final BeerRepository beerRepository;
    private final MapperFacade mapperFacade;

    public Publisher<Beer> beer(final Integer id) {
        return subscriber -> Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(() -> {
                    final Beer beer = beerRepository
                            .findById(id)
                            .map(b -> mapperFacade.map(b, Beer.class))
                            .orElse(null);
                    subscriber.onNext(beer);
                }, 0, 5, TimeUnit.SECONDS);
    }

    public Publisher<List<Beer>> beers() {
        return subscriber -> Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(() -> {
                    final List<Beer> beers = beerRepository
                            .findAll()
                            .stream()
                            .map(beer -> mapperFacade.map(beer, Beer.class))
                            .collect(Collectors.toList());
                    subscriber.onNext(beers);
                }, 0, 5, TimeUnit.SECONDS);
    }

}
