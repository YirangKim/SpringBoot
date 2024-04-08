package org.zerock.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Menu Entity와 동일한 필드를 가지는 MenuDTO를 작성
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

}
