package org.ohgiraffers.board.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    // 2) 해당 메서드는 클라이언트가 보낸 데이터(title과 content)를 담은 CreatePostRequest 객체를 받는다
    private String title;
    private String content;
}
