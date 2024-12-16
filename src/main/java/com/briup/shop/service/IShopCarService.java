package com.briup.shop.service;

import com.briup.shop.bean.ShopCar;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface IShopCarService {
    List<ShopCar> findUserAllShopCar(Long userId);
    void saveShopCar(Long userId,Long shopId);
    void deleteShopCar(Long id);
    void updateShopCar(Long id,int num);
    List<ShopCar> findShopCars(Long[] ids);
}
