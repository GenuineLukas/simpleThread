package com.example.simpleboard.comment.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentRequest {
    @NotNull
    private Long postId;
    @NotBlank
    private String email;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String status;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
