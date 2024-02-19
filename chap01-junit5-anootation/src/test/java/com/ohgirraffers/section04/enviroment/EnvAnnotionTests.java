package com.ohgirraffers.section04.enviroment;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class EnvAnnotionTests {

    /* 수업목표 운영체제. JRE에 따라 테스트를 수행할 수 있다 */
    /* 필기
    * 테스트 메소드는 특정 OS 환겨엥서만 테스트를 진행되게 할 수 있다
    *
    *
    * */

//    @Test
//    @EnabledOnOs(value = OS.MAC, disabledReason = "맥에서만 테스트 합니다")
//    public void testMac(){}
//
//    @Test
//    @EnabledOnOs(value = {OS.WINDOWS, OS.LINUX}, disabledReason = "윈도우와 리눅스만 테스트 합니다")
//    public void testWindowsAndLinux(){}
//
//    @Test
//    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "윈도우 환경에서만 테스트 합니다")
//    public void testWindow(){}

    /* 필기
    * @EnableenOnJre를 이용하여 특정 JRE 버전에서만 테스트 하는 것도 가능하다
    * @DisableOnJre를 이요하여 특정 JRE 버전에서만 테스트 Disabled하는것도 가능하다
    * @EnabledForeJreRange를 이영하면 min과 max 속성 사이의 버전에서 테스트 하는것도 가능하다
    * (min만 작성 시 min-최신버전까지, max만 작성 시 이전 버전부터 max까지만 테스트를 합니다*/

    @Test
    @EnabledOnJre(value = JRE.JAVA_8, disabledReason = "JRE 1.8. 환경에서만 테스트 합니다")
    public void testJRE8(){

    }

    @Test
    @EnabledOnJre(value = {JRE.JAVA_8, JRE.JAVA_17})
    public void testJRE8ANDJRE17(){}

    @Test
    @EnabledOnJre(value = {JRE.JAVA_17})
    public void testDisabledJRE17(){}

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_17)
    public void testFromJRE8TOJRE17(){}
}
