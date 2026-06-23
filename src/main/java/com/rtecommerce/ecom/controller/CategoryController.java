package com.rtecommerce.ecom.controller;

import com.rtecommerce.ecom.model.Category;
import com.rtecommerce.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/api/public/categories")
    public String createCategories(@RequestBody Category category){
        categoryService.createCategory(category );
        return "Category Added Successfully!!";
    }


}
