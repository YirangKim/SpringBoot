package org.zerock.test.dto;

//Menu Entity와 동일한 필드를 가지는 MenuDTO를 작성
public class MenuDTO {

    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

    // 기본 생성자 추가
    public MenuDTO() {}

    public MenuDTO(int menuCode, String menuName, int menuPrice, int categoryCode,
                   String orderableStatus) {
        //super();
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO [menuCode=" + menuCode + ", menuName=" + menuName + ", menuPrice=" + menuPrice
                + ", categoryCode=" + categoryCode + ", orderableStatus=" + orderableStatus + "]";
    }

}
