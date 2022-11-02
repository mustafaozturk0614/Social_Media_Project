package com.bilgeadam.mapper;


import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.graphql.model.UserProfileInput;
import com.bilgeadam.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);


//    @Mapping(source = "id" ,target = "userid")
    UserProfile toUserProfile(final UserProfileResponseDto dto);


    List<UserProfileResponseDto> toUserProfileResponseDtoList(final  List<UserProfile> userProfile);


    UserProfile toUserProfile(final UserProfileInput userProfileInput);

}
