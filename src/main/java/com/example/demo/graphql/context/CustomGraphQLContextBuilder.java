package com.example.demo.graphql.context;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    private final List<MappedBatchLoader<?, ?>> mappedBatchLoaders;

    @Override
    public GraphQLContext build(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext
                .createServletContext()
                .with(buildDataLoaderRegistry())
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();
    }

    @Override
    public GraphQLContext build(final Session session, final HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext
                .createWebSocketContext()
                .with(buildDataLoaderRegistry())
                .with(handshakeRequest)
                .build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        final DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();

        mappedBatchLoaders
                .forEach(mappedBatchLoader -> dataLoaderRegistry
                        .register(mappedBatchLoader.getClass().getName(), DataLoader.newMappedDataLoader(mappedBatchLoader)));

        return dataLoaderRegistry;
    }

}
