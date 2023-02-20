package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.business.gateways.RepositoryExample;
import com.posada.santiago.alphapostsandcomments.domain.commands.AddCommentCommand;
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
class AddCommentNoReactiveTest {


    @Mock
    private RepositoryExample repository;

    private AddCommentNoReactive useCase;


    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new AddCommentNoReactive(repository);
    }

    @Test
    void successfullScenrios(){
        //Arrange
        String POST_ID = "post-id-test";
        String COMMENT_ID = "comment-id-test";
        String AUTHOR = "author-test";
        String CONTENT = "content-test";

        AddCommentCommand command = new AddCommentCommand(POST_ID, COMMENT_ID, AUTHOR, CONTENT);

        /*PostCreated postCreated = new PostCreated(new Title("post-title"), new Author("post-author"), commentId, comment, commentAuthor);
        postCreated.setAggregateRootId(POST_ID);


        Mockito.when(repository.findByIdNoReactivo(POST_ID))
                .thenReturn(List.of(postCreated));


        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(CommentAdded.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        //Act
        List<DomainEvent> result = useCase.apply(command);


        //Assert
        Assertions.assertEquals(command.getPostId(), result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(CommentAdded.class, result.get(0));*/
    }


}