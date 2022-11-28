package com.bilgeadam.controller;

import com.bilgeadam.dto.request.DeleteFollowDto;
import com.bilgeadam.dto.request.FindByToken;
import com.bilgeadam.dto.request.FollowCreateDto;
import com.bilgeadam.dto.response.UserProfilePostResponseDto;
import com.bilgeadam.repository.entity.Follow;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.bilgeadam.constant.ApiUrls.CREATE;
import static com.bilgeadam.constant.ApiUrls.FOLLOW;

@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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

    @PostMapping("/findfollows")
    public ResponseEntity<List<UserProfile>> findFollowsById(@RequestBody FindByToken token, @RequestParam Optional<String> id) {

        return ResponseEntity.ok(followService.findFollowById(token.getToken(), id));
    }


    @GetMapping("/findall")
    public ResponseEntity<List<Follow>> findall() {

        return ResponseEntity.ok(followService.findAll());
    }

    @PostMapping("/findmyfollows")
    public ResponseEntity<List<UserProfilePostResponseDto>> findMyFollow(@RequestBody FindByToken token) {

        return ResponseEntity.ok(followService.findMyFollow(token));
    }
}
