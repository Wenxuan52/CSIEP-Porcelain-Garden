package com.briup.shop.service;

import com.briup.shop.bean.User;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface IUserService {
    User login(String name, String password)throws Exception;
    void register(User user)throws Exception ;
}
