package com.example.simpleboard.post.db;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.comment.db.CommentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 엔티티끼리의 연관관계 설정을 한 상태에서 직접적으로 클라이언트에게 Entity 값이 내려가면
    // 무한궤도에 빠질 수가 있다. (예) PostEntity 에 BoardEntity 가 있고
    // BoardEntity 에서는 또 postEntity 를 참조하기 때문???
    // 결론: 계층간 데이터 교환을 할 때는 Data Transfer Object 형태로 전송
    @ManyToOne //JPA 연관관계 설정: Board 와의 연관관계
    @JsonIgnore //Dto 구현 안할 시 무한 루프 방지 (Board와 Post간)
    @ToString.Exclude //Dto 구현 안할 시 로그 찍을 때 무한 루프 방지 (Board와 Post간)
    private BoardEntity board; //시스템 상으로 board + _id => board_id

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(
        mappedBy = "post"
    )
    @Builder.Default
    private List<CommentEntity> commentLists = List.of();
}
