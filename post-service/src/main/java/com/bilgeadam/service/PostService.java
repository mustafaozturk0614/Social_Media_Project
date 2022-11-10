package com.bilgeadam.service;

import com.bilgeadam.dto.CreatePostDto;
import com.bilgeadam.dto.GetAllPost;
import com.bilgeadam.dto.UserProfilePostResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.PostManagerException;
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

@Service
public class PostService extends ServiceManager<Post, String> {


    private final IPostRepository postRepository;

    private final JwtTokenManager jwtTokenManager;

    private final IUserManager userManager;

    private final CommentService commentService;


    public PostService(IPostRepository postRepository, JwtTokenManager jwtTokenManager, IUserManager userManager, CommentService commentService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userManager = userManager;
        this.commentService = commentService;
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

    private List<GetAllPost> getComment(List<Post> posts) {
        List<GetAllPost> getAllPosts = new ArrayList<>();
        posts.stream().forEach(x -> {
            GetAllPost getAllPost = IPostMapper.INSTANCE.toGetAllPost(x);
            getAllPost.setCommentList(commentService.findAllByPostId(x.getId()).get());
            getAllPosts.add(getAllPost);
        });

        return getAllPosts;
    }
}
