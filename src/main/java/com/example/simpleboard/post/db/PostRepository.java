package com.example.simpleboard.post.db;

import com.example.simpleboard.post.model.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    // select * from post where id = ? and status = ? order b id desc limit 1
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);
    Page<PostEntity> findAllByStatus(String status, Pageable pageable);
}
