package com.bilgeadam.controller;

import com.bilgeadam.dto.request.DeleteFollowDto;
import com.bilgeadam.dto.request.FollowCreateDto;
import com.bilgeadam.repository.entity.Follow;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.ApiUrls.CREATE;
import static com.bilgeadam.constant.ApiUrls.FOLLOW;

@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createFollow(@RequestBody FollowCreateDto dto) {
        followService.create(dto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteFollow(@RequestBody DeleteFollowDto dto) {


        return ResponseEntity.ok(followService.deleteFollow(dto));

    }

    @GetMapping("/findfollows/{token}")
    public ResponseEntity<List<UserProfile>> findFollowsById(@PathVariable String token) {

        return ResponseEntity.ok(followService.findFollowById(token));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Follow>> findall() {

        return ResponseEntity.ok(followService.findAll());
    }
}
