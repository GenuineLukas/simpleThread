package com.example.simpleboard.post.service;

import com.example.simpleboard.comment.model.CommentDto;
import com.example.simpleboard.comment.service.CommentConverter;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostConverter {
    private final CommentConverter commentConverter;  // CommentConverter 주입받기

    public PostDto toDto(PostEntity postEntity) {
        //Dto로 변환할 때 postEntity 에 있던 commentEntity들을 Dto로 변환해줌.
        List<CommentDto> commentDtos = postEntity.getCommentLists().stream()
                .map(commentConverter::toDto)  // 각 코멘트를 CommentDto로 변환
                .collect(Collectors.toList());

        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .boardId(postEntity.getBoard().getId())
                .commentList(commentDtos)  // 변환된 코멘트 리스트 설정
                .build();
    }
}
