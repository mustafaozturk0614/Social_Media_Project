package com.bilgeadam.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
@NotBlank
@Size(min = 3, max = 20 ,message = "Kullanýcý adý en az 3 karakter en fazla 20 karakter olabilir")

private String username;
@NotBlank
@Size(min = 8, max = 32 ,message = "Þifre adý en az 8 karakter en fazla 32 karakter olabilir")
private String password;
@Email(message = "E mail formata uygun deðil")
@NotBlank
private String email;

private  String adminCode;

}
