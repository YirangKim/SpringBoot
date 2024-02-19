package org.ohgiraffers.board.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ohgiraffers.board.domain.dto.*;
import org.ohgiraffers.board.domain.entity.Post;
import org.ohgiraffers.board.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* Service를 인터페이스와 구현체로 나누는 이유
* 1. 다형성과 OCP원칙을 지키기 위해
* 인터페이스와 구현체가 나누어지면, 구현체는 외부로부터 독립되어, 구현체의 수정이나 확장이 자유로워진다.
* 2. 관습적인 추상화 방식
* 과거, spring에서 AOP를 구현할 때 JDK Dynamic Proxy를 사용했는데, 이때 인터페이스가 필수였다.
* 지금은, CGLB를 기본적으로 포함하여 클래스 기반을 프록시 객체를 생성 할 수 있게 되었다.
* */


/* @Transactional
* 선언적으로 트렌젝션 관리를 가능하게 해준다
* 메서드가 실행되는 동안 모든 데이터베이스 연산을 하나의 트렌잭션으로 묶어 처리한다
* 이를 통해, 메서드 내에서 데이터베이스 상태를 변경하는 작업들이 모두 성공적으로 완료되면 그 변경사항을 commit하고
* 하나라도 실패하면 모든 변경사항을 rollback시켜 관리한다.
*
* transaction
* 데이터베이스의 상태를 변화시키기 위해 수행하는 작업의 단위
*/
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 3 PostService의 createPost 메서드 호출:
    // createPost 메서드는 받은 데이터로 새로운 게시물을 만들고, 이를 데이터베이스에 저장합니다.
    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request){
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        Post savePost = postRepository.save(post); //save return값을 돌려줌

        return new CreatePostResponse(savePost.getPostId(), savePost.getTitle(), savePost.getContent());
    }

    public ReadPostResponse readPostById(Long postId){

        //postId있는지 없는지 체크
        Post foundPost = postRepository.findById(postId)
                    //예외 오류 처리
                    .orElseThrow(() -> new EntityNotFoundException("해당 postId로 조회된 게시글이 없습니다"));

        return new ReadPostResponse(foundPost.getPostId(), foundPost.getTitle(), foundPost.getContent());
    }

    @Transactional //DB commit .... 공부필요
    // post맨은 반영, DB에 반영안됐던 문제 Transactional
    public UpdatePostResponse updatePost(Long postId, UpdatePostRequest request){

        // postId있는지 없는지 체크
        Post foundPost = postRepository.findById(postId)
                //예외 오류 처리
                .orElseThrow(() -> new EntityNotFoundException("해당 postId로 조회된 게시글이 없습니다"));

        // Dirty Checking
        // 바꿔온 엔티티를 jps가 감지하고 바꿔줌
        foundPost.update(request.getTitle(), request.getContent());
        return new UpdatePostResponse(foundPost.getPostId(), foundPost.getTitle(), foundPost.getContent());
    }

    @Transactional
    public DeletePostResponse deletePost(Long postId){
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 postId로 조회된 게시글이 없습니다"));
        postRepository.delete(foundPost);
        return new DeletePostResponse(foundPost.getPostId());
    }

    // 조회
    public Page<ReadPostResponse> readAllPost(Pageable pageable){
        Page<Post> postsPage = postRepository.findAll(pageable);
        return postsPage.map(post -> new ReadPostResponse( //map 공부
                post.getPostId(),
                post.getTitle(),
                post.getContent()
        ));
    }

}
