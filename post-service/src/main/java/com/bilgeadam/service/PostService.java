package com.bilgeadam.service;

import com.bilgeadam.dto.CreatePostDto;
import com.bilgeadam.dto.GetAllPost;
import com.bilgeadam.dto.UserProfilePostResponseDto;
import com.bilgeadam.dto.request.DeletePostDto;
import com.bilgeadam.dto.request.FindByToken;
import com.bilgeadam.dto.request.GetOtherUserPost;
import com.bilgeadam.dto.request.PostUpdateDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.PostManagerException;
import com.bilgeadam.manager.IFollowManager;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IPostMapper;
import com.bilgeadam.repository.IPostRepository;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService extends ServiceManager<Post, String> {


    private final IPostRepository postRepository;

    private final JwtTokenManager jwtTokenManager;

    private final IUserManager userManager;
    private final IFollowManager followManager;

    private final CommentService commentService;


    public PostService(IPostRepository postRepository, JwtTokenManager jwtTokenManager, IUserManager userManager, CommentService commentService, IFollowManager followManager) {
        super(postRepository);
        this.postRepository = postRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userManager = userManager;
        this.commentService = commentService;
        this.followManager = followManager;
    }

    public Post create(CreatePostDto dto) {

        return save(IPostMapper.INSTANCE.toPost(dto));

    }

    public List<GetAllPost> getMyPost(String token) {
        Optional<Long> authId = jwtTokenManager.getUserId(token);
        if (authId.isPresent()) {
            UserProfilePostResponseDto dto = userManager.findbyAuthId(authId.get()).getBody();
            List<Post> posts = postRepository.findAllByUserId(dto.getId());
            return getComment(posts);
        } else {

            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public List<GetAllPost> getOtherUserPost(GetOtherUserPost getOtherUserPost) {
        Optional<Long> authId = jwtTokenManager.getUserId(getOtherUserPost.getToken());
        if (authId.isPresent()) {
            List<Post> posts = postRepository.findAllByUsername(getOtherUserPost.getUsername());
            return getComment(posts);
        } else {
            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public List<GetAllPost> getComment(List<Post> posts) {
        List<GetAllPost> getAllPosts = new ArrayList<>();
        posts.stream().forEach(x -> {
            GetAllPost getAllPost = IPostMapper.INSTANCE.toGetAllPost(x);
            getAllPost.setCommentList(commentService.findAllByPostId(x.getId()).get());
            getAllPosts.add(getAllPost);
        });
        return getAllPosts;
    }

    public Boolean deletePost(DeletePostDto deletePostDto) {
        Optional<Long> authid = jwtTokenManager.getUserId(deletePostDto.getToken());
        if (authid.isPresent()) {
            UserProfilePostResponseDto dto = userManager.findbyAuthId(authid.get()).getBody();
            Optional<Post> post = postRepository.findByIdAndUserId(deletePostDto.getPostId(), dto.getId());
            if (post.isPresent()) {

                delete(post.get());
                return true;
            } else {
                throw new PostManagerException(ErrorType.POST_NOT_FOUND);
            }

        } else {
            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public Boolean updatePost(PostUpdateDto postUpdateDto) {
        Optional<Long> authId = jwtTokenManager.getUserId(postUpdateDto.getToken());

        if (authId.isPresent()) {
            UserProfilePostResponseDto dto = userManager.findbyAuthId(authId.get()).getBody();
            Optional<Post> post = postRepository.findByIdAndUserId(postUpdateDto.getId(), dto.getId());
            if (post.isPresent()) {
                post.get().setPostMediaUrl(postUpdateDto.getPostMediaUrl());
                post.get().setContent(postUpdateDto.getContent());
                post.get().setTitle(postUpdateDto.getTitle());
                save(post.get());
                return true;
            } else {

                throw new PostManagerException(ErrorType.POST_NOT_FOUND);
            }


        } else {

            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }

    }

    public List<Post> myFollowPost(FindByToken token) {

     
        try {

            List<String> userIds = followManager.findMyFollowPost(token).getBody().stream().map((x) -> x.getId()).collect(Collectors.toList());

            return postRepository.findByUserIdIn(userIds);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
