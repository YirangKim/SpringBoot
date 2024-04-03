package org.zerock.test.controllter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.test.dto.MenuDTO;
import org.zerock.test.service.MenuService;

@Controller //빈 스캐닝
@RequestMapping("/menu")
public class MenuController {

    //MenuService 타입을 생성자 주입
    private final MenuService menuService;

    public MenuController(MenuService menuSerivce) {
        this.menuService = menuSerivce;
    }

    @GetMapping("{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model){
        //1 menuService.findMenuByCode() 메서드로 메뉴 코드를 전달하여 메뉴 하나의 조회를 요청
        MenuDTO menu = menuService.findMenuByCode(menuCode);
        //2 반환 된 MenuDTO 는 Model 객체에 담고
        model.addAttribute("menu", menu);
        //3 menu/detail 뷰로 응답
        return "menu/detail";
    }

}
