package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.FollowCreateDto;
import com.bilgeadam.dto.response.UserProfilePostResponseDto;
import com.bilgeadam.repository.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IFollowMapper {


    IFollowMapper INSTANCE = Mappers.getMapper(IFollowMapper.class);


    Follow toFollow(final FollowCreateDto dto);

    @Mapping(source = "userId", target = "id")
    UserProfilePostResponseDto toUserProfilePostResponseDto(Follow follow);

}
