package org.ohgiraffers.board.service;

import lombok.RequiredArgsConstructor;
import org.ohgiraffers.board.domain.dto.*;
import org.ohgiraffers.board.domain.entity.Userlog;
import org.ohgiraffers.board.repository.BloodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BloodService {

    private final BloodRepository bloodRepository;

    // 3 PostService의 createPost 메서드 호출:
    // createPost 메서드는 받은 데이터로 새로운 게시물을 만들고, 이를 데이터베이스에 저장합니다.
    @Transactional
    public CreateUserLogResponce createBlood(CreateUserLogRequest request) {
        Userlog user_log = Userlog.builder()
                .name(request.getName())
                .profile_im(request.getProfile_im())
                .age(request.getAge())
                .weight(request.getWeight())
                .height(request.getHeight())
                .gender(request.getGender())
                .avblood(request.getAvblood())
                .afterblood(request.getAfterblood())
                .build();

        Userlog saveBlood = bloodRepository.save(user_log); //save return값을 돌려줌

        return new CreateUserLogResponce(saveBlood.getId(), saveBlood.getName(), saveBlood.getProfile_im(),
                saveBlood.getAge(), saveBlood.getWeight(), saveBlood.getHeight(), saveBlood.getGender(),
                saveBlood.getAvblood(), saveBlood.getAfterblood());
    }
}
