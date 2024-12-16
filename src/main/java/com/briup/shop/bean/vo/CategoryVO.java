package com.briup.shop.bean.vo;

import com.briup.shop.bean.Category;
import lombok.Data;

import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
@Data
public class CategoryVO {
    private Long id;
    private String name;
    private List<Category> categories;

    public CategoryVO(Category category, List<Category> categories) {
        this.id=category.getId();
        this.name=category.getName();
        this.categories=categories;

    }
}
