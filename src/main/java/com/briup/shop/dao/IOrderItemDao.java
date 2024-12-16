package com.briup.shop.dao;

import com.briup.shop.bean.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface IOrderItemDao extends JpaRepository<OrderItem,Long> {
}
