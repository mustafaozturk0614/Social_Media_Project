package com.bilgeadam.controller;

import com.bilgeadam.dto.request.FollowCreateDto;
import com.bilgeadam.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
