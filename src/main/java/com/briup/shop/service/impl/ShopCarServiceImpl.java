package com.briup.shop.service.impl;

import com.briup.shop.bean.Shop;
import com.briup.shop.bean.ShopCar;
import com.briup.shop.dao.IShopCarDao;
import com.briup.shop.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
@Service
public class ShopCarServiceImpl implements IShopCarService {
    @Autowired
    private IShopCarDao shopCarDao;
    @Override
    public List<ShopCar> findUserAllShopCar(Long userId) {

        List<ShopCar> shopCarList = shopCarDao.findByUserId(userId);
        return shopCarList;
    }

    @Override
    public void saveShopCar(Long userId,Long shopId) {
        ShopCar shopCar = shopCarDao.findByShopIdAndUserId(shopId, userId);
        if (shopCar==null){
            shopCarDao.saveByshopIdUserId(shopId,userId);
        }else {
            shopCarDao.updateShopcar(shopCar.getNum()+1,shopCar.getId());
        }

    }

    @Override
    public void deleteShopCar(Long id) {
    shopCarDao.deleteById(id);
    }

    @Override
    public void updateShopCar(Long id, int num) {
        shopCarDao.updateShopcar(num,id);
    }

    @Override
    public List<ShopCar> findShopCars(Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        List<ShopCar> shopCarList = shopCarDao.findAllById(list);
        return  shopCarList;
    }
}
