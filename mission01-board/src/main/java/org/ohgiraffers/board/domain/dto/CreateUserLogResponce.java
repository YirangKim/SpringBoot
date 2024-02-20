package org.ohgiraffers.board.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserLogResponce {

    private Long id;
    private String name;
    private String profile_im;
    private Integer age;
    private Integer weight;
    private Integer height;
    private String gender;
    private Integer avblood;
    private Integer afterblood;
}
