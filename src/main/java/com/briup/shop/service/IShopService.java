package com.briup.shop.service;

import com.briup.shop.bean.Shop;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface IShopService {
    List<Shop> findAllShops();
    Shop findShopById(Long id);
    List<Shop> findDiscount();
    List<Shop> recommendShop(Long userId);
    List<Shop> findByCategory(Long category);

    List<Shop> searchShop(String shopName);
}
