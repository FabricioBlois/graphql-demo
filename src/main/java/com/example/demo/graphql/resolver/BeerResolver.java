package com.example.demo.graphql.resolver;

import com.example.demo.graphql.response.Beer;
import com.example.demo.graphql.response.Brand;
import com.example.demo.graphql.response.Message;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.MessageRepository;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BeerResolver implements GraphQLResolver<Beer> {

    private final TaskExecutor taskExecutor;
    private final MapperFacade mapperFacade;
    private final BrandRepository brandRepository;
    private final MessageRepository messageRepository;

    public CompletableFuture<Brand> getBrand(final Beer instance) {
        return CompletableFuture.supplyAsync(() -> brandRepository
                        .findById(instance.getBrandId())
                        .map(brand -> mapperFacade.map(brand, Brand.class))
                        .orElse(null),
                taskExecutor);
    }

    public CompletableFuture<List<Message>> getMessages(final Beer instance) {
        return CompletableFuture.supplyAsync(() -> messageRepository
                        .findAllByBeerId(instance.getId())
                        .stream()
                        .map(message -> mapperFacade.map(message, Message.class))
                        .collect(Collectors.toList()),
                taskExecutor);
    }

}
