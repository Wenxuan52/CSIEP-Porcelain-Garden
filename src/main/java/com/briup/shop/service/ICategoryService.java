package com.briup.shop.service;

import com.briup.shop.bean.vo.CategoryVO;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
public interface ICategoryService {
    List<CategoryVO> findAllCategoey();
}
