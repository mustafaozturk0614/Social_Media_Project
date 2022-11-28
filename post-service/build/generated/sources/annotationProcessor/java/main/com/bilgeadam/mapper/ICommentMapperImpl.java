package com.bilgeadam.mapper;

import com.bilgeadam.dto.CreateCommentDto;
import com.bilgeadam.repository.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T10:39:23+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class ICommentMapperImpl implements ICommentMapper {

    @Override
    public Comment toComment(CreateCommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.userId( dto.getUserId() );
        comment.postId( dto.getPostId() );
        comment.content( dto.getContent() );
        comment.username( dto.getUsername() );

        return comment.build();
    }
}
