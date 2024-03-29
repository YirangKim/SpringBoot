package org.ohgiraffers.board.controller;

import lombok.RequiredArgsConstructor;
import org.ohgiraffers.board.domain.dto.*;
import org.ohgiraffers.board.service.BloodService;
import org.ohgiraffers.board.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RestController
@RequestMapping("/api/v2/posts") // RequestMapping 기본경로
@RequiredArgsConstructor
public class UserController {

    private final BloodService bloodService;

    @PostMapping //post 요청
    public ResponseEntity<CreateUserLogResponce> bloodCreate(
            @RequestBody CreateUserLogRequest request) { // request 형식 요청

        CreateUserLogResponce response = bloodService.createBlood(request);

        return new ResponseEntity<>(response, HttpStatus.OK); //연결에 대한 상태
    }
}
