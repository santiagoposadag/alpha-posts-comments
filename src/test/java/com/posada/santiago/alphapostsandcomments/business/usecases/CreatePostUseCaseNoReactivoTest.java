package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.business.gateways.RepositoryExample;
import com.posada.santiago.alphapostsandcomments.domain.commands.CreatePostCommand;
import com.posada.santiago.alphapostsandcomments.domain.events.CommentAdded;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CreatePostUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private CreatePostUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new CreatePostUseCaseNoReactivo(repository);
    }

    @Test
    void successfulScenario(){
        String POST_ID = "test-post-id";
        String AUTHOR = "test-author";
        String TITLE = "test-title";
        //Arrange
        CreatePostCommand command = new CreatePostCommand(POST_ID,
                AUTHOR,
                TITLE);

        PostCreated event = new PostCreated(new Title(TITLE), new Author(AUTHOR));
        event.setAggregateRootId(POST_ID);

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(PostCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Act
        List<DomainEvent> result = useCase.apply(command);


        //Assert
        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());
    }
}