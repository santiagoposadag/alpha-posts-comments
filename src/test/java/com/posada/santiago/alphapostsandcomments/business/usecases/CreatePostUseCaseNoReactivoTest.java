package com.posada.santiago.alphapostsandcomments.business.usecases;

import com.posada.santiago.alphapostsandcomments.business.gateways.RepositoryExample;
import com.posada.santiago.alphapostsandcomments.domain.commands.CreatePostCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        /*PostCreated event = new PostCreated(new Title(TITLE), new Author(AUTHOR), commentId, comment, commentAuthor);
        event.setAggregateRootId(POST_ID);

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(PostCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Act
        List<DomainEvent> result = useCase.apply(command);


        //Assert
        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());*/
    }
}