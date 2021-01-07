package com.example.demo.graphql.dataloader;

import com.example.demo.graphql.response.Message;
import com.example.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.dataloader.MappedBatchLoader;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

@Component
@RequiredArgsConstructor
public class MessageMappedBatchLoader implements MappedBatchLoader<Integer, Set<Message>> {

    private final TaskExecutor taskExecutor;
    private final MapperFacade mapperFacade;
    private final MessageRepository messageRepository;

    @Override
    public CompletionStage<Map<Integer, Set<Message>>> load(final Set<Integer> ids) {
        return CompletableFuture.supplyAsync(() -> messageRepository
                        .findAllByBeerIdIn(ids)
                        .stream()
                        .map(message -> mapperFacade.map(message, Message.class))
                        .collect(groupingBy(Message::getBeerId, toCollection(LinkedHashSet::new))),
                taskExecutor);
    }

}
