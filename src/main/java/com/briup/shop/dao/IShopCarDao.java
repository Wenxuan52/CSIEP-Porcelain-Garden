package com.briup.shop.dao;

import com.briup.shop.bean.ShopCar;
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
public interface IShopCarDao extends JpaRepository<ShopCar, Long> {
	ShopCar findByShopIdAndUserId(long shopId, long userId);

	@Transactional
	@Modifying
	@Query( value="update ShopCar set num=?1 where id=?2")
	void updateShopcar(int num, long id);

	List<ShopCar> findByUserId(long id);

	void deleteById(long id);

	@Modifying
	@Query(value = "insert into t_shop_car(shop_id,user_id,num) values(?1,?2,1)" ,nativeQuery = true)
	@Transactional
	void saveByshopIdUserId(long shopId, long userId);
}
