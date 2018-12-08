package com.community.lombok.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
/**
 * 특정 필드만 적용하고 싶다면?
 * @EqualsAndHashCode(of = "{name}")
 */
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor // @NotNull이 적용된 필드값만 인자로 받는 생성자
public class Human {

    private String name;
    private int age;



}

