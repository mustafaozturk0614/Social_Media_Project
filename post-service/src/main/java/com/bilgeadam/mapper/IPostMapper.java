package com.bilgeadam.mapper;

import com.bilgeadam.dto.CreatePostDto;
import com.bilgeadam.repository.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPostMapper {


    IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);


    Post toPost(final CreatePostDto dto);
}
