package com.bilgeadam.controller;

import com.bilgeadam.dto.CreatePostDto;
import com.bilgeadam.dto.GetAllPost;
import com.bilgeadam.dto.request.DeletePostDto;
import com.bilgeadam.dto.request.GetOtherUserPost;
import com.bilgeadam.dto.request.PostUpdateDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.PostManagerException;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.ApiUrls.*;

@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createPost(@RequestBody CreatePostDto dto) {
        try {
            postService.create(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new PostManagerException(ErrorType.POST_NOT_CREATED);
        }
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }


    @GetMapping("/getmypost/{token}")
    public ResponseEntity<List<GetAllPost>> getMyPost(@PathVariable String token) {


        return ResponseEntity.ok(postService.getMyPost(token));
    }

    @PostMapping("/getotheruserpost")
    public ResponseEntity<List<GetAllPost>> getMyPost(@RequestBody GetOtherUserPost getOtherUserPost) {


        return ResponseEntity.ok(postService.getOtherUserPost(getOtherUserPost));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deletePost(@RequestBody DeletePostDto deletePostDto) {

        return ResponseEntity.ok(postService.deletePost(deletePostDto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updatePost(@RequestBody PostUpdateDto postUpdateDto) {

        return ResponseEntity.ok(postService.updatePost(postUpdateDto));
    }
}
