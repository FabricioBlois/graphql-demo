package com.example.demo.util;

import graphql.schema.DataFetchingEnvironment;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.dataloader.MappedBatchLoader;
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DataLoaderUtil {

    public static <K, V, M extends MappedBatchLoader<K, V>> CompletableFuture<V> load(
            @Nullable final K key,
            @NonNull final Class<M> loaderClass,
            @NonNull final DataFetchingEnvironment dfe) {
        return Optional.ofNullable(key)
                .map(k -> dfe.<K, V>getDataLoader(loaderClass.getName()).load(key))
                .orElseGet(() -> CompletableFuture.completedFuture(null));
    }

}
