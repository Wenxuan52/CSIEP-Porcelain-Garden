package com.briup.shop.service;

import com.briup.shop.bean.Order;
import com.briup.shop.bean.User;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface IOrderService {

    List<Order> findUserAllOrders(Long userId);
    Order saveOrder(Long [] shopCarIds, User user,Long addressId);

    Order paySuccess(String orderId);

    Order findById(String orderId);
}
