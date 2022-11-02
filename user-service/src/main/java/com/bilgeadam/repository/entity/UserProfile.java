package com.bilgeadam.repository.entity;


import com.bilgeadam.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class UserProfile implements Serializable {


  String id;
  Long authid;
  String username;
  String name;
  String email;
  String phone;
  String photo;
  String address;
  String about;
  @Builder.Default
  Long created=System.currentTimeMillis();
  Long updated;

  @Builder.Default
  Status status= Status.PENDING;



}
