package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.NewCreateUserDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.AuthListResponseDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.dto.response.RoleResponseDto;
import com.bilgeadam.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);
    RegisterRequestDto toRegisterRequestDto(final Auth auth);
    Auth toAuth(final RegisterRequestDto dto);

    Auth toAuth(final  LoginRequestDto dto);

    LoginResponseDto toLoginResponseDto(final Auth auth);

    RegisterResponseDto toRegisterResponseDto(final Auth auth);

    NewCreateUserDto toNewCreateUserDto(final  Auth auth);

    RoleResponseDto toRoleResponseDto(final  Auth auth);

    List<AuthListResponseDto> tAuthListResponseDtoList(final List<Auth> auths);


}
