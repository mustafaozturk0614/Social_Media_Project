package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRedisResponseDto implements Serializable {
    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    Status status;
}
