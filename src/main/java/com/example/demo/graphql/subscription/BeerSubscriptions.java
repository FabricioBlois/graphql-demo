package com.example.demo.graphql.subscription;

import com.example.demo.domain.Beer;
import com.example.demo.repository.BeerRepository;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BeerSubscriptions implements GraphQLSubscriptionResolver {

    private final BeerRepository beerRepository;

    public Publisher<Beer> beer(final Integer id) {
        return subscriber -> Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(() -> {
                    final Beer beer = beerRepository
                            .findById(id)
                            .orElse(null);
                    subscriber.onNext(beer);
                }, 0, 5, TimeUnit.SECONDS);
    }

    public Publisher<List<Beer>> beers() {
        return subscriber -> Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(() -> {
                    final List<Beer> beers = beerRepository.findAll();
                    subscriber.onNext(beers);
                }, 0, 5, TimeUnit.SECONDS);
    }

}
