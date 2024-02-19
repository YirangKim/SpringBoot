package com.ohgirraffers.section05.custom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// Retention -> 어노테이션이 언제까지 유지될지 명시하는 것
@Target(ElementType.METHOD)
// Target -> 이 어노이테이 적용될 수 있는 JAVA요소의 종류를 지정합니다
@EnabledOnOs( value = OS.WINDOWS, disabledReason = "윈도우 환경에서 테스트 합니다")
@Test
public @interface WindowsTest {
}
