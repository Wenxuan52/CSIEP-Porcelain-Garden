package com.briup.shop.service.impl;

import com.briup.shop.bean.Collect;
import com.briup.shop.bean.Shop;
import com.briup.shop.bean.User;
import com.briup.shop.dao.ICollectDao;
import com.briup.shop.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
@Service
public class CollectService implements ICollectService {
    @Autowired
    private ICollectDao collectDao;

    @Override
    public List<Collect> findUserAllCollect(Long id) {
        List<Collect> collects = collectDao.findByUserId(id);
        return collects;
    }

    @Override
    public void deleteCollect(Long shopId, Long userId) {
        collectDao.deleteByShopIdAndUserId(shopId, userId);
    }

    @Override
    public void deleteCollect(Long id) {
        collectDao.deleteById(id);
    }

    @Override
    public void addCollect(User user, Long shopId) {
        Collect collect = new Collect();
        Shop shop = new Shop();
        shop.setId(shopId);
        collect.setShop(shop);
        collect.setUser(user);
        collectDao.save(collect);
    }

    @Override
    public boolean findCollect(Long userId, Long shopId) {
        Collect collect = collectDao.findByUserIdAndShopId(userId, shopId);
        if (collect == null)
            return false;
        else
            return true;
    }

    @Override
    public Collect findOne(Long collectId) {
        return collectDao.getById(collectId);
    }
}
