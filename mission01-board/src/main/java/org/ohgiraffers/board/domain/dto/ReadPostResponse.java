package org.ohgiraffers.board.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor //필드 모든 변수 포함
@NoArgsConstructor //기본 생성자
public class ReadPostResponse {

    private Long postId;
    private String title;
    private String content;
}
