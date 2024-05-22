package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.comment.db.CommentRepository;
import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagination;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PostConverter postConverter;

    public PostDto create(
            PostRequest postRequest
    ){
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get(); // << 임시 고정
        var entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .title(postRequest.getTitle())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();
        var savedPost = postRepository.save(entity);
        return postConverter.toDto(savedPost);
    }
    /*
    1. 게시글이 있는가
    2. 비밀번호가 맞는가
     */
    public PostDto view(PostViewRequest postViewRequest) {
        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
        .map(it -> {
        if(!it.getPassword().equals(postViewRequest.getPassword())){
            var format = "패스워드가 맞지 않습니다 %s vs %s";
            throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
        }
        return postConverter.toDto(it);
         }).orElseThrow(
                ()-> {
                    return new RuntimeException("해당 게시글이 존재하지 않습니다." + postViewRequest.getPostId());
                }
        );
    }

    public Api<List<PostDto>> all(Pageable pageable) {
        var list = postRepository.findAllByStatus("REGISTERED", pageable);

        var convertedList = list.stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var response = Api.<List<PostDto>>builder()
                .body(convertedList)
                .pagination(pagination)
                .build();

        return response;
    }


    public void delete(PostViewRequest postViewRequest){
         postRepository.findById(postViewRequest.getPostId())
            .map(it -> {
                if(!it.getPassword().equals(postViewRequest.getPassword())){
                    var format = "패스워드가 맞지 않습니다 %s vs %s";
                    throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                }

                it.setStatus("UNREGISTERED");
                postRepository.save(it);
                return it;
            }).orElseThrow(
                ()-> {
                    return new RuntimeException("해당 게시글이 존재하지 않습니다." + postViewRequest.getPostId());
                }
        );
    }
}
