package com.briup.shop.service.impl;

import com.briup.shop.bean.Category;
import com.briup.shop.bean.vo.CategoryVO;
import com.briup.shop.dao.ICategoryDao;
import com.briup.shop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author adam
 * @date 2022/1/12
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryDao categoryDao;
    @Override
    public List<CategoryVO> findAllCategoey() {
        ArrayList<CategoryVO> vos = new ArrayList<>();
        List<Category> first = categoryDao.findByParentIdIsNull();
        for (Category category : first) {
            List<Category> second = categoryDao.findByParentId(category.getId());
            CategoryVO categoryVO = new CategoryVO(category,second);
            vos.add(categoryVO);
        }

        return vos;
    }
}
