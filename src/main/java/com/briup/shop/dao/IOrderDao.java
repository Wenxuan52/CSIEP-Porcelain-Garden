package com.briup.shop.dao;

import com.briup.shop.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adam
 * @date  2022/1/12
 */
@Repository
public interface IOrderDao extends JpaRepository<Order, String> {
	List<Order> findByUserId(Long id);

}
