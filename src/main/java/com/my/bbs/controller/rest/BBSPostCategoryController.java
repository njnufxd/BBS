package com.my.bbs.controller.rest;

import com.my.bbs.dao.BBSPostCategoryMapper;
import com.my.bbs.entity.BBSPostCategory;
import com.my.bbs.service.BBSPostCategoryService;
import com.my.bbs.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BBSPostCategoryController {
    @Resource
    BBSPostCategoryService bbsPostCategoryService;

    @Resource
    BBSPostCategoryMapper bbsPostCategoryMapper;
    @PostMapping("/addCategory")
    @ResponseBody
    public Result<BBSPostCategory> addCategory(@RequestBody String categoryName){
        System.out.println(categoryName);
        if(bbsPostCategoryMapper.selectByName(categoryName)!=null){
            return new Result<>(400,"板块已存在");
        }
        BBSPostCategory category=new BBSPostCategory();
        category.setCategoryName(categoryName);
        if (bbsPostCategoryMapper.create(categoryName)>0){
            return new Result<>(200,category);
        }
        return new Result<>(300,"新建失败");
    }
    @PostMapping("/deleteCategory")
    @ResponseBody
    public Result<BBSPostCategory> deleteCategory(@RequestBody String categoryName){
        System.out.println();
        if (bbsPostCategoryMapper.deleteByName(categoryName)>0){
            return new Result<>(200,"删除成功");
        }
        return new Result<>(300,"删除失败");
    }

    @GetMapping("/category/all")
    @ResponseBody
    public List<BBSPostCategory> categoryList(){
        return bbsPostCategoryService.getBBSPostCategories();
    }
}
