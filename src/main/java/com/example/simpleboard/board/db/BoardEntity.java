package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String boardName;

    String status;

    @OneToMany(
        // 엔티티끼리의 연관관계 설정을 한 상태에서 직접적으로 클라이언트에게 Entity 값이 내려가면
        // 무한궤도에 빠질 수가 있다. (예) PostEntity 에 BoardEntity 가 있고
        // BoardEntity 에서는 또 postEntity 를 참조하기 때문???
        // 결론: 계층간 데이터 교환을 할 때는 Data Transfer Object 형태로 전송
        mappedBy = "board" //PostEntity 에 가면 이 변수가 존재해야 한다.
    )
    @Builder.Default
    private List<PostEntity> postList = List.of();
}
