package com.example.simpleboard.comment.controller;

import com.example.simpleboard.comment.db.CommentEntity;
import com.example.simpleboard.comment.model.CommentDto;
import com.example.simpleboard.comment.model.CommentRequest;
import com.example.simpleboard.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("")
    public CommentDto create(
        @Valid
        @RequestBody CommentRequest commentRequest
        ){
        return commentService.create(commentRequest);
    }
}
