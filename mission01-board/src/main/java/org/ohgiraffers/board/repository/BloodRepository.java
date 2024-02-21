package org.ohgiraffers.board.repository;

import org.ohgiraffers.board.domain.entity.User_log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRepository extends JpaRepository<User_log, Long> {
}
