package com.briup.shop.dao;

import com.briup.shop.bean.RecommendShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author adam
 * @date 2022/1/13
 */
@Repository
public interface IRecommendShopDao extends JpaRepository<RecommendShop,Long> {
   List<RecommendShop> findByUserId(Long userId);
}
