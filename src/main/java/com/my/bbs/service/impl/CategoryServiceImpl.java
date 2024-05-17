package com.my.bbs.service.impl;

import com.my.bbs.dao.CategoryMapper;
import com.my.bbs.entity.Category;
import com.my.bbs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getBBSPostCategories() {
        return categoryMapper.getBBSPostCategories();
    }

    @Override
    public Category selectById(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }
}
