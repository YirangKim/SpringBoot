package com.ohgirraffers.section03.addtional;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdditionalAnnotationTests {

    /* Junit에서 제공하는 테스트 관련 추가 어노테이션을 사용할 수 있다 */
    /* 필기
    * 해당 테스트 를 무시할 때 사용하는 어노테이션 이다
    * Junitdp
    * */

    @Disabled
    @Test
    public void testIgnore(){}

    /* Timeout 필기
    * 주어진 시간안에 테스트가 끝나지 않으면 테스트가 실패한다
    * vlaue에는 시간을 정수형으로, unit 사용할 시간 단위를 기술한다
    * */
    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testTimeout() throws InterruptedException {
        //스드레가 일정시간동안(value) 멈추게 함
        Thread.sleep(900);
    }

    /* @Tag 필기
    * @Tag태그를 이용하여 테스트를 필터링 할 수 있다
    * @Tag를 사용하기 위한 규칙
    * 1. 태그는 공백이나 null이 잇으면 안된다
    * 2. 다음 글자들은 포함하면 안된다 , ( ) & ! |
    * */

    @Test
    @Tag("development")
    public void testTag1(){}

    @Test
    @Tag("production")
    public void testTag2(){}

    @Test
    @Tags(value = {@Tag("development"), @Tag("production")})
    public void testTag3(){}

    /* 필기
    * 테스트 메소드는 실행 순서가 보장되지 않지만 경우에 따라서는(통합테스트 환경 등) 테스트의 순서가 중요한 경우도 있다
    * 클래스 레벨에 @TestMethodOrder(MethodOrderer.OrderAnnotation.class) 어노테이션을 추가하고
    * 각 테스트 메소드에 @Order 어노테이션으로 순서를 지정하면 테스트 순서를 설정할 수 있다
    * 클래스에 작성한 테스트 메소드의 순서는 MethodOrder에 DisplayName, MethodName, OrderAnnotation, Random 등이 있다.
    * */

    @Test
    @Order(1)
    public void testFirst(){}

    @Test
    @Order(2)
    public void testSecond(){}

    @Test
    @Order(3)
    public void testThird(){}
}
