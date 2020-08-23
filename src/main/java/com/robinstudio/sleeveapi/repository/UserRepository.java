package com.robinstudio.sleeveapi.repository;

import com.robinstudio.sleeveapi.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, Long> {
    UserDO findByEmail(String email);
    UserDO findByOpenid(String openid);
    UserDO findFirstById(Integer id);
    UserDO findByUnifyUid(Integer uuid);
}
