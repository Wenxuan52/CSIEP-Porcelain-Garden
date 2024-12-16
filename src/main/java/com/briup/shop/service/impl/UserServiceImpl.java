package com.briup.shop.service.impl;

import com.briup.shop.bean.User;
import com.briup.shop.dao.IUserDao;
import com.briup.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author adam
 * @date 2022/1/13
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User login(String name, String password) throws Exception {
        User user = userDao.findByLoginName(name);
        if (user == null) {
            throw new Exception("用户未注册");
        } else {
            if (user.isLock()) {
                throw new Exception("用户已锁定 请联系管理员");
            }
            if (user.getPasswordMd5().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
                return user;
            } else {
                throw new Exception("用户名密码错误");
            }
        }
    }

    @Override
    public void register(User user) throws Exception {
        User usera = userDao.findByLoginName(user.getLoginName());
        if (usera==null){
            userDao.save(user);
        }else {
            throw new  Exception("用户名已经存在，请更换后重试");
        }

    }
}
