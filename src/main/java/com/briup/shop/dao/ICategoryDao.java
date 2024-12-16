package com.briup.shop.dao;

import com.briup.shop.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface ICategoryDao  extends JpaRepository<Category,Long> {
    List<Category> findByParentIdIsNull();
    List<Category> findByParentId(Long id);
}
