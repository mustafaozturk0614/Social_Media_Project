package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.Roles;
import com.bilgeadam.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthListResponseDto {

    private Long id;
    private String username;
    private String email;
    private Roles role;
    private Status status;

}
