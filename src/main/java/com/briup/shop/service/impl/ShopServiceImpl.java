package com.briup.shop.service.impl;

import com.briup.shop.bean.RecommendShop;
import com.briup.shop.bean.Shop;
import com.briup.shop.dao.IRecommendShopDao;
import com.briup.shop.dao.IShopDao;
import com.briup.shop.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author adam
 * @date 2022/1/12
 */
@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopDao shopDao;
    @Autowired
    private IRecommendShopDao recommendShopDao;

    @Override
    public List<Shop> findAllShops() {
        List<Shop> all = shopDao.findByDiscountOrderBySalesVolumeDesc(false);
        return all;
    }

    @Override
    public Shop findShopById(Long id) {
        return shopDao.getById(id);
    }

    @Override
    public List<Shop> findDiscount() {
        List<Shop> discountList = shopDao.findByDiscountOrderBySalesVolumeDesc(true);
        return discountList;
    }

    @Override
    public List<Shop> recommendShop(Long userId) {
        List<RecommendShop> recommendShops = recommendShopDao.findByUserId(userId);
        if (recommendShops.isEmpty()) {
            List<Shop> shops = shopDao.findAll(Sort.by(Sort.Direction.DESC,"visitVolume")).subList(0, 10);
            return shops;
        } else {
            List<Shop> shops = recommendShops.stream().sorted((x,y)->x.getRecommandValue()>y.getRecommandValue()?1:-1).map(i->i.getShops()).limit(8).collect(Collectors.toList());
           return shops;
        }
    }

    @Override
    public List<Shop> findByCategory(Long categoryId) {
        List<Shop> shops = shopDao.findByCategoryId(categoryId);
        return shops;
    }

    @Override
    public List<Shop> searchShop(String shopName) {

        return shopDao.findByNameContaining(shopName.toUpperCase());
    }
}
