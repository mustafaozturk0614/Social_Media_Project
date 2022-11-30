package com.bilgeadam.manager;

import com.bilgeadam.dto.UserProfilePostResponseDto;
import com.bilgeadam.dto.request.FindByToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "${myapplication.feign.user}/follow", name = "user-service-follow", decode404 = true)
public interface IFollowManager {


    @PostMapping("/findmyfollows")
    public ResponseEntity<List<UserProfilePostResponseDto>> findMyFollowPost(@RequestBody FindByToken mytoken);


}
