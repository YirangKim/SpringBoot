package org.zerock.test.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.zerock.test.dto.MenuDTO;

@Builder
@Getter
@Setter
@ToString
public class Pagenation {

    public static PagingButtonInfo getPagingButtonInfo(Page<MenuDTO> page) {

        int currentPage = page.getNumber() + 1;
        int defaultButtonCount = 10;
        int startPage;
        int endPage;

        startPage = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1)
                * defaultButtonCount + 1;
        endPage = startPage + defaultButtonCount - 1;

        if(page.getTotalPages() < endPage)
            endPage = page.getTotalPages();

        if(page.getTotalPages() == 0 && endPage == 0)
            endPage = startPage;

        return new PagingButtonInfo(currentPage, startPage, endPage);
    }

}
