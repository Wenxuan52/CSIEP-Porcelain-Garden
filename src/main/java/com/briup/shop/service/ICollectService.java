package com.briup.shop.service;

import com.briup.shop.bean.Collect;
import com.briup.shop.bean.User;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface ICollectService {
    List<Collect> findUserAllCollect(Long id);
    void deleteCollect(Long userId,Long shopId);
    void deleteCollect(Long id);

    void addCollect(User user, Long shopId);

    boolean findCollect(Long id, Long shopId);

    Collect findOne(Long collectId);
}
