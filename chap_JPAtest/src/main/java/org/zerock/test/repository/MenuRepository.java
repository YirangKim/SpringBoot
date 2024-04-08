package org.zerock.test.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.test.domain.Menu;

import java.util.List;

//JpaRepository 인터페이스에는 기본적은 crud 기능과 페이징, 정렬
//제네릭 선언이 사용할 Entity 타입과 해당 Entity의 Id의 type을 작성한다.

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /** Query Method는 JPQL을 메소드로 대신 처리할 수 있도록 제
     * 메소드의 이름으로 필요한 쿼리를 만들어주는 기능
     * find + 엔티티 이름(엔티티이름생략가능) + By + 변수 이름 : findByMenuPriceGreaterThan
     * */

    //전달 되는 가격을 초과하는 메뉴의 목록을 조회, 정렬
    //List<Menu> findByMenuPriceGreaterThan(Integer menuPrice);
    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice, Sort sort);

    //전달 되는 가격을 초과하는 메뉴의 목록을 가격 순으로 조회
    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(Integer menuPrice);
}
