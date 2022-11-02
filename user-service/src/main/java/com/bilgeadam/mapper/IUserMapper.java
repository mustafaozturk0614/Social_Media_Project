package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.NewCreateUserDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.UserProfileRedisResponseDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserMapper {


    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);


    UserProfile toUserProfile(final NewCreateUserDto dto);


    UserProfile toUserProfile(final UpdateRequestDto dto);

    UserProfileRedisResponseDto toUserProfileRedisResponseDto(final UserProfile userProfile);

    UserProfileResponseDto toUserProfileResponseDto(final  UserProfile userProfile);
    List<UserProfileResponseDto> toUserProfileResponseDtoList(final  List<UserProfile> userProfile);

}
