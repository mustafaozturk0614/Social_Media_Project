package com.bilgeadam.service;

import com.bilgeadam.dto.CreateCommentDto;
import com.bilgeadam.mapper.ICommentMapper;
import com.bilgeadam.repository.ICommentRepository;
import com.bilgeadam.repository.entity.Comment;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment, String> {


    private final ICommentRepository commentRepository;


    public CommentService(ICommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }

    public Comment create(CreateCommentDto dto) {
        return save(ICommentMapper.INSTANCE.toComment(dto));
    }


    public Optional<List<Comment>> findAllByPostId(String id) {
        return commentRepository.findAllOptionalByPostId(id);
    }
}
