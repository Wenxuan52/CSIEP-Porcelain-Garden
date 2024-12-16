package com.briup.shop.dao;

import com.briup.shop.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author adam
 * @date  2022/1/12
 */
@Repository
public interface IUserDao extends JpaRepository<User,Long > {
    User findByLoginName(String loginName);
}
