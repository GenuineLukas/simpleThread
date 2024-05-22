package com.example.simpleboard.comment.service;

import com.example.simpleboard.comment.db.CommentEntity;
import com.example.simpleboard.comment.db.CommentRepository;
import com.example.simpleboard.comment.model.CommentDto;
import com.example.simpleboard.comment.model.CommentRequest;
import com.example.simpleboard.post.db.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentConverter commentConverter;

    public CommentDto create(
            CommentRequest commentRequest
    ){
        var postEntity = postRepository.findById(commentRequest.getPostId()).get();

        var entity = CommentEntity.builder()
                .post(postEntity)
                .email(commentRequest.getEmail())
                .userName(commentRequest.getUserName())
                .password(commentRequest.getPassword())
                .status("REGISTERED")
                .title(commentRequest.getTitle())
                .content(commentRequest.getContent())
                .commentedAt(LocalDateTime.now())
                .build()
                ;
        var saveComment =  commentRepository.save(entity);
        return commentConverter.toDto(saveComment);
    }
}
