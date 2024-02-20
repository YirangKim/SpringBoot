package org.ohgiraffers.board.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ohgiraffers.board.domain.dto.*;
import org.ohgiraffers.board.domain.entity.Post;
import org.ohgiraffers.board.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    /** @Mock
     * 가짜 객체, 테스트 실행 시 실제가 아닌 Mock객체를 반환하다
     * */

    @Mock
    private PostRepository postRepository;

    /** @InjectMocks
     * Mock 객체가 주입될 클래스를 지정한다
     * */
    @InjectMocks
    private PostService postService;

    private Post savedPost;
    private Post post;
    private CreatePostRequest createPostRequest;
    private UpdatePostRequest updatePostRequest;

    @BeforeEach
    void setup() {
        // 초기화
        post = new Post(1L, "테스트 제목", "테스트 내용");
        savedPost = new Post(2L, "저장되어 있던 테스트 제목", "저장되어 있던 테스트 내용");
        createPostRequest = new CreatePostRequest("테스트 제목", "테스트 내용");
        updatePostRequest = new UpdatePostRequest("변경된 테스트 제목", "변경된 테스트 내용");
    }

    //작성
    @Test
    @DisplayName("게시글 작성 기능 테스트")
    void create_post_test(){

        //given
        //mockito 기본 형태
        //when(postRepository.save(any())).thenReturn(post);
        //BDDMockito 형태
        given(postRepository.save(any())).willReturn(post);

        //when
        CreatePostResponse createPostResponse = postService.createPost(createPostRequest);

        //then
        assertThat(createPostResponse.getPostId()).isEqualTo(1L);
        assertThat(createPostResponse.getTitle()).isEqualTo("테스트 제목");
        assertThat(createPostResponse.getContent()).isEqualTo("테스트 내용");
    }

    //조회
    @Test
    @DisplayName("postId로 게시글을 조회하는 기능 테스트")
    void read_post_by_id_1(){

        //given 준비
        //postRepository.findById(any())이 호출
        //어떤 ID가 들어오더라도 항상 savedPost 객체를 반환
        when(postRepository.findById(any())).thenReturn(Optional.of(savedPost));

        //when 실행
        //postService.readPostById(savedPost.getPostId())를 호출
        //특정 ID의 게시글을 읽어옵니다.
        ReadPostResponse readPostResponse = postService.readPostById(savedPost.getPostId());

        //then 확인
        //readPostResponse 객체의 게시글 ID, 제목, 내용이
        // 각각 savedPost 객체의 게시글 ID, 제목, 내용과 일치하는지 확인
        assertThat(readPostResponse.getPostId()).isEqualTo(savedPost.getPostId());
        assertThat(readPostResponse.getTitle()).isEqualTo(savedPost.getTitle());
        assertThat(readPostResponse.getContent()).isEqualTo(savedPost.getContent());
    }

    // postId 게시글 찾지 못했을 때
    @Test
    @DisplayName("postId로 게시글을 찾지 못했을 때, 지정한 Exception을 발생시키는지 테스트")
    void read_post_by_id_2(){

        //given
        given(postRepository.findById(any())).willReturn(Optional.empty());

        //when & then
        assertThrows(EntityNotFoundException.class, () ->
                postService.readPostById(1L));
    }

    // 게시글 조회
    // postService의 readAllPost 메서드 동작 확인
    // 목 객체를 사용하여 postRepository.findAll(pageable)이 호출될 때 가상의 페이지를 반환
    // findAll이 올바르게 호출되는지 테스트
    @Test
    @DisplayName("전체 게시글 조회 기능 테스트")
    void read_all_post(){

        //given
        Pageable pageable = PageRequest.of(0,5); //객체 만들고 페이지 번호 크기 설정
        List<Post> posts = Arrays.asList(post, savedPost); //가짜 Post 객체 두 개를 만들어서 리스트에 넣습니다
        Page<Post> postPage = new PageImpl<>(posts, pageable, posts.size()); //PageImpl<> 가상페이지 만들고, Pageable 설정

        given(postRepository.findAll(pageable)).willReturn(postPage); //가상 페이지 반환

        //when
        Page<ReadPostResponse> responses = postService.readAllPost(pageable); //postService.readAllPost(pageable)을 호출하여 전체 게시글 가져옴

        //then
        //확인
        assertThat(responses.getContent()).hasSize(2);
        assertThat(responses.getContent().get(0).getTitle()).isEqualTo("테스트 제목");
        assertThat(responses.getContent().get(0).getContent()).isEqualTo("테스트 내용");
        assertThat(responses.getContent().get(1).getContent()).isEqualTo("저장되어 있던 테스트 내용");
        assertThat(responses.getContent().get(1).getContent()).isEqualTo("저장되어 있던 테스트 내용");
    }

    //게시물 수정
    @Test
    @DisplayName("게시물 수정")
    void update_post_test(){

        //given
        given(postRepository.findById(any())).willReturn(Optional.of(savedPost));

        //when
        UpdatePostResponse updatePostResponse = postService.updatePost(savedPost.getPostId(), updatePostRequest);

        //then
        assertThat(updatePostResponse.getPostId()).isEqualTo(savedPost.getPostId());
        assertThat(updatePostResponse.getTitle()).isEqualTo("변경된 텍스트 제목");
        assertThat(updatePostResponse.getContent()).isEqualTo("변경된 텍스트 내용");
    }

    //게시글 삭제
    @Test
    @DisplayName("postId로 게시글 삭제")
    void delete_post_test(){

        //given 준비
        //postRepository.findById(any()) 호출
        //어떤 ID가 들어오더라도 항상 savedPost 객체 반환 Return
        when(postRepository.findById(any())).thenReturn(Optional.of(savedPost));

        //when 실행
        //postService.deletePost(savedPost.getPostId()) 호출
        //deletePostResponse(return) 특정 ID의 게시글을 읽어옵니다.
        DeletePostResponse deletePostResponse = postService.deletePost(savedPost.getPostId());

        //then 확인
        //assertThat 검증
        //deletePostResponse.getPostId() 객체의 게시글 ID
        //각각 savedPost 객체 게시글 ID 일치하는지 확인
        assertThat(deletePostResponse.getPostId()).isEqualTo(2L);

    }

    //게시글 전체 조회
}
