package com.posada.santiago.alphapostsandcomments.application.adapters.repository;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.application.generic.models.StoredEvent;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.JSONMapper;
import com.posada.santiago.alphapostsandcomments.business.gateways.DomainEventRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Repository
public class MongoEventStoreRepository implements DomainEventRepository {

    private final ReactiveMongoTemplate template;

    private final JSONMapper eventSerializer;

    public MongoEventStoreRepository(ReactiveMongoTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Flux<DomainEvent> findById(String aggregateId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateId));
        return template.find(query, DocumentEventStored.class)
                .sort(Comparator.comparing(event -> event.getStoredEvent().getOccurredOn()))
                .map(storeEvent -> storeEvent.getStoredEvent().deserializeEvent(eventSerializer));
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event){
        DocumentEventStored eventStored = new DocumentEventStored();
        eventStored.setAggregateRootId(event.aggregateRootId());
        eventStored.setStoredEvent(StoredEvent.wrapEvent(event, eventSerializer));
        return template.save(eventStored)
                .map(storeEvent -> storeEvent.getStoredEvent().deserializeEvent(eventSerializer));
    }
}
