package org.ohgiraffers.board.repository;

import org.ohgiraffers.board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// 4 게시물 생성 및 저장:
// createPost 메서드에서는 PostRepository를 사용하여 새로운 게시물을 저장합니다.
public interface PostRepository extends JpaRepository<Post, Long> {

}
