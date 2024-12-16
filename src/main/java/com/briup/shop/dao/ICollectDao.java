package com.briup.shop.dao;

import com.briup.shop.bean.Collect;
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
public interface ICollectDao extends JpaRepository<Collect, Long> {
	List<Collect> findByUserId(long id);
	@Query("delete from Collect where shop.id =?1 and user.id=?2 ")
	@Transactional
	@Modifying
	Collect deleteByShopIdAndUserId(long shopId, long userId);

    Collect findByUserIdAndShopId(Long userId, Long shopId);
}
