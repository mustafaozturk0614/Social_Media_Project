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

import static com.bilgeadam.constant.ApiUrls.CREATE;
import static com.bilgeadam.constant.ApiUrls.FOLLOW;

@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FollowController {

    private final FollowService followService;

    @PostMapping(CREATE)
    public ResponseEntity<Follow> createFollow(@RequestBody FollowCreateDto dto) {
        ;
        return ResponseEntity.ok(followService.create(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Follow> deleteFollow(@RequestBody DeleteFollowDto dto) {

        System.out.println(dto);
        return ResponseEntity.ok(followService.deleteFollow(dto));

    }

    @PostMapping("/findfollows")
    public ResponseEntity<List<UserProfile>> findFollowsById(@RequestBody FindByToken token) {

        return ResponseEntity.ok(followService.findFollowById(token.getToken(), token.getId()));
    }

    @PostMapping("/findfollowsbytoken")
    public ResponseEntity<List<Follow>> findFollowsByToken(@RequestBody FindByToken token) {

        return ResponseEntity.ok(followService.findFollowByToken(token.getToken()));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Follow>> findall() {

        return ResponseEntity.ok(followService.findAll());
    }

    @PostMapping("/findmyfollows")
    public ResponseEntity<List<UserProfilePostResponseDto>> findMyFollowPost(@RequestBody FindByToken mytoken) {

        return ResponseEntity.ok(followService.findMyFollowPost(mytoken));
    }
}
