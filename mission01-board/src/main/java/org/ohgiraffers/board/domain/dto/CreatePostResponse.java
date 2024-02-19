package org.ohgiraffers.board.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponse {

    // 5 저장된 게시물 정보를 바탕으로 새로운 CreatePostResponse 객체를 만듭니다.
    // 이 객체에는 새로운 게시물의 정보가 담겨 있습니다.
    // CreatePostResponse 객체를 사용하여 클라이언트에게 응답을 보냅니다.
    private Long postId;
    private String title;
    private String content;
}
