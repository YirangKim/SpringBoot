package org.zerock.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.test.domain.Category;
import org.zerock.test.domain.Menu;

import java.util.List;

/**카테고리 테이블을 조회하기 위해 CategoryRepository interface를 작성
 *  findAll 메소드를 활용
 * Native Query를 사용해서 구현
 * */

public interface CategoryRepository extends JpaRepository <Category, Integer>{

    /* JPQL 작성시에는 value만 작성해도 되지만
    Native Query 작성시에는 반드시 nativeQuery = true 속성을 정의해야 한다. */
    @Query(value="SELECT category_code, category_name, ref_category_code FROM tbl_category"
            +" ORDER BY category_code ASC"
            , nativeQuery = true)
    public List<Category> findAllCategory();

}
