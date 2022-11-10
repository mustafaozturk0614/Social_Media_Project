package com.bilgeadam.mapper;

import com.bilgeadam.dto.CreatePostDto;
import com.bilgeadam.dto.GetAllPost;
import com.bilgeadam.repository.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPostMapper {


    IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);


    Post toPost(final CreatePostDto dto);

    List<GetAllPost> toGetAllPosts(final List<Post> posts);

    GetAllPost toGetAllPost(final Post post);
}
