package com.example.demo.graphql.dataloader;

import com.example.demo.graphql.response.Brand;
import com.example.demo.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.dataloader.MappedBatchLoader;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public class BrandMappedBatchLoader implements MappedBatchLoader<Integer, Brand> {

    private final TaskExecutor taskExecutor;
    private final MapperFacade mapperFacade;
    private final BrandRepository brandRepository;

    @Override
    public CompletionStage<Map<Integer, Brand>> load(final Set<Integer> ids) {
        return CompletableFuture.supplyAsync(() -> brandRepository
                        .findAllByIdIn(ids)
                        .stream()
                        .map(brand -> mapperFacade.map(brand, Brand.class))
                        .collect(toMap(Brand::getId, Function.identity())),
                taskExecutor);
    }

}
