package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.exceptions.JSONSerilizationException;
import com.posada.santiago.alphapostsandcomments.business.gateways.DomainEventRepository;
import com.posada.santiago.alphapostsandcomments.business.gateways.EventBus;
import com.posada.santiago.alphapostsandcomments.domain.events.CommentAdded;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.CommentId;
import com.posada.santiago.alphapostsandcomments.domain.values.Content;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class AddCommentEventUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private AddCommentEventUseCase useCase;


    @BeforeEach
    void setUp(){
        useCase = new AddCommentEventUseCase(repository, bus);
    }

    @Test
    void successfulScenario() throws JSONSerilizationException {
        //Arrange
        final String AGGREGATE_ID = "test-post-id";

        PostCreated event = new PostCreated(
                "Test title",
                "Test post Author",
                "test-comment-id",
                "test content",
                "test comment author"
        );
        event.setAggregateRootId(AGGREGATE_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(CommentAdded.class))).thenAnswer(
                invocationOnMock -> Mono.just(invocationOnMock.getArgument(0))
        );

        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        //Act
        Flux<DomainEvent> result = useCase.apply(Mono.just(event));

        StepVerifier.create(result)
                //Assert
                .expectNextMatches(event1 ->{
                        CommentAdded commentAdded = (CommentAdded) event1;
                        Assertions.assertEquals(commentAdded.getId().value(), event.getCommentId());
                        return event1.aggregateRootId().equals(event.aggregateRootId()); })
                .verifyComplete();
    }
}