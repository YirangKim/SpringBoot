package com.ohi.pram.section01.pram;

import java.time.Month;

public class DateValidator {

    //받아온 매개변수 Month가 1~12월 사이인지 확인을 하고, 맞다면 true반환하고 틀리면 false 반환
    public static boolean isCollect(Month month){

        int monthValue = month.getValue(); //getValue String -> int 숫자로 바꿈
        return monthValue >= 1 && monthValue <= 12;

    }

    //max
    public static int getLastDayof(Month month){

        return month.maxLength();
    }
}
