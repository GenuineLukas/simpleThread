package com.example.simpleboard.comment.service;

import com.example.simpleboard.comment.db.CommentEntity;
import com.example.simpleboard.comment.model.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentConverter {
    public CommentDto toDto(CommentEntity commentEntity){
        return CommentDto.builder()
                .id(commentEntity.getId())
                .password(commentEntity.getPassword())
                .status(commentEntity.getStatus())
                .title(commentEntity.getTitle())
                .content(commentEntity.getContent())
                .email(commentEntity.getEmail())
                .userName(commentEntity.getUserName())
                .commentedAt(commentEntity.getCommentedAt())
                .postId(commentEntity.getPost().getId())
                .build()
                ;
    }
}
