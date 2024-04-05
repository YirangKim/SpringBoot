package org.zerock.test.service;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class PagingButtonInfo {

    private int currentPage;
    private int startPage;
    private int endPage;

    public PagingButtonInfo() {}

    public PagingButtonInfo(int currentPage, int startPage, int endPage) {
        super();
        this.currentPage = currentPage;
        this.startPage = startPage;
        this.endPage = endPage;
    }

}
