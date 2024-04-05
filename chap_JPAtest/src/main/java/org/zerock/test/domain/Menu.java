package org.zerock.test.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

//Entity 생성
//JPA를 사용할 것이므로 테이블 tbl_menu 와 매핑 될 Menu Entity를 생성
@Log4j2
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name="menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name="menu_price")
    private int menuPrice;
    @Column(name="category_code")
    private int categoryCode;
    @Column(name="orderable_status")
    private String orderableStatus;

//    public int getMenuCode() {
//        return menuCode;
//    }
//
//    public void setMenuCode(int menuCode) {
//        this.menuCode = menuCode;
//    }
//
//    public String getMenuName() {
//        return menuName;
//    }
//
//    public void setMenuName(String menuName) {
//        this.menuName = menuName;
//    }
//
//    public int getMenuPrice() {
//        return menuPrice;
//    }
//
//    public void setMenuPrice(int menuPrice) {
//        this.menuPrice = menuPrice;
//    }
//
//    public int getCategoryCode() {
//        return categoryCode;
//    }
//
//    public void setCategoryCode(int categoryCode) {
//        this.categoryCode = categoryCode;
//    }
//
//    public String getOrderableStatus() {
//        return orderableStatus;
//    }
//
//    public void setOrderableStatus(String orderableStatus) {
//        this.orderableStatus = orderableStatus;
//    }

    @Override
    public String toString() {
        return "Menu [menuCode=" + menuCode + ", menuName=" + menuName + ", menuPrice=" + menuPrice
                + ", categoryCode=" + categoryCode + ", orderableStatus=" + orderableStatus + "]";
    }
}
