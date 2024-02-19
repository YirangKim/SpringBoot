package com.ohgirraffers.section01.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestAnnotationTests {

    /* 수업 목표. Junit5의 기본 어노테이션을 사용할 수 있다 */
    /* 필기
    * Junit5는 세개의 서브 프로젝트로 이루어져 있다.
    * 1. Junit Platform
    * -JVM에서 테스트 프레임워크를 실행하기 위한 테스트 엔진을 제공
    * 2. Junit Jupiter
    * - Junit5에서 테스트를 작성하고 실행하기 위한 엔진 제공
    * 3. Junit5 Vintage
    * - Junit3, 4를 기반으로 돌아가는 테스트 엔진을 제공해준다 (하위 호환용)
     */
    /*
    * 테스트 클래스는 적어도 한개 이상의 @Test어노테이션이 달린 메소드를 가진 클래스이다.
    * 테스트 클래스는 abstract이면 안되고, 한개 이상의 생성자를 가지고 있어야 한다.
    *
    * (두개 이상 작성하면 랜타임시 PrecoditionViolationException 이 발생한다)
    * 아무런 생성자도 작성하지 않으면 기본 생성자는 컴파일러가 자동으로 추가된다
    * */

    public TestAnnotationTests(){}

    //public TestAnnotationTests(int value){}

    /*
     * 필기. 기본적으로 텍스트 이름은 메소드 이름을 따라가지만 @DisplayName에 설정한 이름으로 이름을 표기해줌
     */


    @Test
    @DisplayName("테스트 메소드 1 ")
    public void testMethod1(){
    }

    @Test
    public void 테스트_메소드2(){
        /* 필기
        * 클래스 레벨에 @DisplayNameGenerator을 붙이게 되면 언더바를 공백으로 처리하여 테스트 이름을 바꿔줄수 있다
        * 단,
        * */
    }
}
