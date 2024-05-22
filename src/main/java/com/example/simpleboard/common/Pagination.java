package com.example.simpleboard.common;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
    private Integer page;
    private Integer size;
    private Integer currentElements;
    private Integer totalPage;
    private Long totalElements; //totalElements 는 Integer 로 감당가능한 범위가 넘을 수 있으므로..
}
