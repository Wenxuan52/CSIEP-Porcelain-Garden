package com.briup.shop.dao;


import com.briup.shop.bean.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author adam
 * @date  2022/1/12
 */
public interface IShippingAddressDao extends JpaRepository<ShippingAddress,Long> {
    List<ShippingAddress> findByUserId(Long userId);
}
