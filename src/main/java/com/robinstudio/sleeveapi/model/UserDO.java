package com.robinstudio.sleeveapi.model;

import com.robinstudio.sleeveapi.util.MapAndJson;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Where(clause = "delete_time is null")
@Table(name = "user", schema = "sleeve-api", catalog = "")
public class UserDO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String openid;
    private String nickname;
    private Integer unifyUid;
    private String email;
    private String password;
    private String mobile;

    // private Short group; // 分组 - 等级 group/level/scope

    @Convert(converter = MapAndJson.class)
    private Map<String, Object> wxProfile;
}
