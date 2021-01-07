package com.example.demo.graphql.resolver;

import com.example.demo.graphql.dataloader.BrandMappedBatchLoader;
import com.example.demo.graphql.dataloader.MessageMappedBatchLoader;
import com.example.demo.graphql.response.Beer;
import com.example.demo.graphql.response.Brand;
import com.example.demo.graphql.response.Message;
import com.example.demo.util.DataLoaderUtil;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
public class BeerResolver implements GraphQLResolver<Beer> {

    public CompletableFuture<Brand> getBrand(final Beer instance, final DataFetchingEnvironment dfe) {
        return DataLoaderUtil.load(instance.getBrandId(), BrandMappedBatchLoader.class, dfe);
    }

    public CompletableFuture<Set<Message>> getMessages(final Beer instance, final DataFetchingEnvironment dfe) {
        return DataLoaderUtil
                .load(instance.getId(), MessageMappedBatchLoader.class, dfe)
                .thenApply(result -> ObjectUtils.defaultIfNull(result, Set.of()));
    }

}
