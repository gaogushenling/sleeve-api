package com.robinstudio.sleeveapi.dto;

import com.robinstudio.sleeveapi.validators.PasswordEqual;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;

@Getter
@Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @RequiredArgsConstructor
@Builder // 使用 @Builder 注解，会私有化，无法 new 实例，还要加上 @NoArgsConstructor
// @Data
@PasswordEqual(min=3)
public class PersonDTO {
    // @NonNull
    @Length(min=1, max=10)
    private String name;

    private Integer age;

    @Valid // 级联校验
    private SchoolDTO school;

    private String password1;
    private String password2;
}
