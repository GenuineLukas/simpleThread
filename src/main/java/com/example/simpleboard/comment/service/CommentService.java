package com.example.simpleboard.comment.service;

import com.example.simpleboard.comment.db.CommentEntity;
import com.example.simpleboard.comment.db.CommentRepository;
import com.example.simpleboard.comment.model.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentEntity create(
            CommentRequest commentRequest
    ){
        var entity = CommentEntity.builder()
                .postId(commentRequest.getPostId())
                .email(commentRequest.getEmail())
                .userName(commentRequest.getUserName())
                .password(commentRequest.getPassword())
                .status("REGISTERED")
                .title(commentRequest.getTitle())
                .content(commentRequest.getContent())
                .commentedAt(LocalDateTime.now())
                .build()
                ;

        return commentRepository.save(entity);
    }

    public List<CommentEntity> findAllByPostId(Long postId){
        return commentRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
    }
}
