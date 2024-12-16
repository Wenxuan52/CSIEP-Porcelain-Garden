package com.briup.shop.dao;

import com.briup.shop.bean.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author adam
 * @date  2022/1/12
 */
@Repository
public interface IShopDao extends JpaRepository<Shop, Long> {

    @Modifying
    @Query("update  Shop set salesVolume =?2 where id=?1")
    @Transactional
    void updateSalesVolume(Long shopId,Long salesVolume);

    @Modifying
    @Query("update  Shop set visitVolume =?2 where id=?1")
    @Transactional
    void updateVisitVolume(Long shopId,Long visitVolume);

    List<Shop> findByDiscountOrderBySalesVolumeDesc(boolean discount);

    List<Shop> findByCategoryId(Long categoryId);

    List<Shop> findByNameContaining(String shopName);
}
