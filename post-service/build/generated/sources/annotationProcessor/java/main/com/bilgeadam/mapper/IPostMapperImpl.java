package com.bilgeadam.mapper;

import com.bilgeadam.dto.CreatePostDto;
import com.bilgeadam.repository.entity.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-09T10:54:03+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IPostMapperImpl implements IPostMapper {

    @Override
    public Post toPost(CreatePostDto dto) {
        if ( dto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.userId( dto.getUserId() );
        post.username( dto.getUsername() );
        post.title( dto.getTitle() );
        post.content( dto.getContent() );
        post.postMediaUrl( dto.getPostMediaUrl() );

        return post.build();
    }
}
