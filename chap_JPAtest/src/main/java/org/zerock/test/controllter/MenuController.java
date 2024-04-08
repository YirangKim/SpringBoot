package org.zerock.test.controllter;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.test.domain.Menu;
import org.zerock.test.dto.CategoryDTO;
import org.zerock.test.dto.MenuDTO;
import org.zerock.test.repository.MenuRepository;
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
@Transactional
@RequestMapping("/menu")
public class MenuController {

    //MenuService 타입을 생성자 주입
    private final MenuService menuService;
    private final MenuRepository menuRepository;

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
    @Transactional(readOnly = true) //조회
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
    @Transactional(readOnly = true) //조회
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

    /** 메뉴등록
     *  파라미터로 전달 받고 MenuService에 구현한 메소드를 호출
     *  별도의 Exception 발생이 없었다면
     *  클라이언트가 메뉴 목록을 조회하는 기능을 다시 요청할 수 있도록 redirect 처리
     * */
    @PostMapping("/regist")
    public String registNewMenu(MenuDTO newMenu){
        menuService.registNewMenu(newMenu);
        log.info("newMenu 는: {}", newMenu);
        return "redirect:/menu/list";
    }

    /** 메뉴 수정
     *  @PostMapping 을 사용하고,  메뉴 코드, 메뉴 이름을 MenuDTO 타입으로 전달 받는다.
     *  전달 받은 값을 MenuService에 구현한 메소드로 전달
     *  별도의 Exception 발생이 없었다면
     *  클라이언트가 수정한 메뉴의 상세 페이지를 볼 수 있도록 redirect
     * */

    @GetMapping("/modify")
    public void modifyPage() {}

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modifyMenu){
        menuService.modifyMenu(modifyMenu);
        log.info("modifyMenu는 : {}", modifyMenu);
        return "redirect:/menu/" + modifyMenu.getMenuCode();
    }

    //메뉴 삭제
    @GetMapping("/delete")
    public void deletePage() {}

    @PostMapping("/delete")
    public String deletMenu(@RequestParam Integer menuCode){
        menuService.deleteMenu(menuCode);
        return "redirect:/menu/list";
    }

}
