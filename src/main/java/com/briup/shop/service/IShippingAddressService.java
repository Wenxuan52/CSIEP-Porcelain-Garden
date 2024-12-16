package com.briup.shop.service;

import com.briup.shop.bean.ShippingAddress;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface IShippingAddressService {
    void saveShippingAddress(ShippingAddress shippingAddress);
    List<ShippingAddress> findUserAllShippingAddress(Long id);
}
