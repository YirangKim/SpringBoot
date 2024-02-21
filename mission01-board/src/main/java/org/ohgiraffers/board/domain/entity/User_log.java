package org.ohgiraffers.board.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class User_log {

    @Id
    @GeneratedValue
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