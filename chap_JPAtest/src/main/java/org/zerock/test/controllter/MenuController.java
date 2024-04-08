package org.zerock.test.controllter;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.test.dto.CategoryDTO;
import org.zerock.test.dto.MenuDTO;
import org.zerock.test.service.MenuService;
import org.zerock.test.service.Pagenation;
import org.zerock.test.service.PagingButtonInfo;

import java.util.List;
@Builder
@Getter
@Setter
@ToString
@Log4j2
@Controller //빈 스캐닝
@RequestMapping("/menu")
public class MenuController {

    //MenuService 타입을 생성자 주입
    private final MenuService menuService;

    @GetMapping("{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model){
        //1 menuService.findMenuByCode() 메서드로 메뉴 코드를 전달하여 메뉴 하나의 조회를 요청
        MenuDTO menu = menuService.findMenuByCode(menuCode);
        //2 반환 된 MenuDTO 는 Model 객체에 담고
        model.addAttribute("menu", menu);
        //3 menu/detail 뷰로 응답
        return "menu/detail";
    }

    /** 전체 조회 후 페이징 처리
     * menuService.findMenuList() 메서드로 메뉴 전체 목록 조회를 요청
     * @PageableDefault는 컨트롤러 메서드에서 Pageable 파라미터의 기본 값
     * @PageableDefault 페이지 크기를 20, 페이지 번호를 0, 정렬 방향을 ASCENDING으로 기본 지정
     * parameter로 Pageable을 선언하면 요청 시 전달한 number, size, sort 파라미터가 담긴다.
     * 반환 되는 Page 객체가 가지는 값들을 확인하기 위해서 로그도 출력
     * 게시글 하단에 나타나는 페이징 버튼을 만들기 위해서는
     * 페이징 버튼이 노출 되는 페이징 바에서
     * 현재 요청 페이지와 함께 노출 되어야 하는 시작 페이지와 끝 페이지를 계산
     * 이를 위해 PagingButtonInfo 클래스와 Pagenation 클래스를 작
     * */
    @GetMapping("list")
    public String findMenuList(@PageableDefault(size = 5) Pageable pageable, Model model){

        //parameter로 Pageable을 선언하면 요청 시 전달한 number, size, sort 파라미터가 담긴다.
        log.info("pageable 요청 내용 : {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);

        log.info("조회한 내용 목록 : {}", menuList.getContent());
        log.info("총 페이지 수 : {}", menuList.getTotalPages());
        log.info("총 메뉴 수 : {}", menuList.getTotalElements());
        log.info("해당 페이지에 표시 될 요소 수 : {}", menuList.getSize());
        log.info("해당 페이지에 실제 요소 수 : {}", menuList.getNumberOfElements());
        log.info("첫 페이지 여부 : {}", menuList.isFirst());
        log.info("마지막 페이지 여부 : {}", menuList.isLast());
        log.info("정렬 방식 : {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(menuList);

        model.addAttribute("paging", paging);
        model.addAttribute("menuList", menuList);

        return "menu/list";
    }


    /** 전체조회 basic 버전
     * menuService.findMenuList() 메서드로 메뉴 전체 목록 조회를 요청
     * 반환 된 List<MenuDTO> 는 Model 객체에 담고
     * menu/list 뷰로 응답
     * */

//    @GetMapping("list")
//    public String findMenuList(Model model){
//
//        List<MenuDTO> menuList = menuService.findMenulist();
//        model.addAttribute("menuList", menuList);
//
//        return "menu/list";
//    }

    //쿼리 메소드 실행
    @GetMapping("/querymethod")
    public void queryMethodPage() {}

    /**전달 되는 가격을 초과하는 메뉴의 목록을 조회
     * menuService.findByMenuPrice() 메서드로 기준 메뉴 가격을 전달하며
     * 기준에 맞는 메뉴 목록 조회를 요청한다.
     * List<MenuDTO> 는 Model 객체에 담고 menu/searchResult 뷰로 응답
     * */
    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam Integer menuPrice, Model model ){
        List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);
        model.addAttribute("menuPrice", menuPrice);

        log.info("menuList: {}", menuList);
        log.info("menuPrice: {}", menuPrice);

        return "menu/searchResult";
    }

    //Create 등록
    @GetMapping("/regist")
    public void registPage() {}

    /** MenuController의 카테고리 목록 조회 메소드
     *  조회 된 List<CategoryDTO> 데이터를 응답하기 위해  @ResponseBody 어노테이션
     *  */
    @GetMapping(value="/category", produces="application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {

        return menuService.findAllCategory();
    }

}
