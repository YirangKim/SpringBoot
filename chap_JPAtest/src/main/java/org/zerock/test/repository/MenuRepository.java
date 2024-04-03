package org.zerock.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.test.domain.Menu;

//JpaRepository 인터페이스에는 기본적은 crud 기능과 페이징, 정렬
//제네릭 선언이 사용할 Entity 타입과 해당 Entity의 Id의 type을 작성한다.

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
