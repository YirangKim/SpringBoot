package org.zerock.test.service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.test.domain.Menu;
import org.zerock.test.dto.MenuDTO;
import org.zerock.test.repository.MenuRepository;

// Service에서는 Repository에 구현 된 DB에 접근하는 여러 메소드들을 호출한 뒤
// 해당 결과를 Controller로 리턴할 것이다
// Menu Entity와 동일한 필드를 가지는 MenuDTO를 작성한다.
@Service
public class MenuService {

    //MenuRepository타입을 생성자 주입 받는
    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    // MenuService에서 해당 빈을 의존성 주입 받아 쓰고 싶으므로
    // 생성자를 이용한 의존성 주입을 하도록 기존의 코드를 수정
    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper){
        
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
    }

    // findById 메소드는 id 값을 전달하여 해당 id의 엔터티를 조회
    // findById 의 반환값은 Optional 타입
    // 전달 된 id로 조회 된 Menu 엔터티가 없을 경우에는 IllegalArgumentException을 발생
    // ModelMapper의 map 메소드를 통해 Menu Entity를 MenuDTO로 변환해서 반환
    public MenuDTO findMenuByCode(int menuCode) {
        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(menu, MenuDTO.class);
    }
}
