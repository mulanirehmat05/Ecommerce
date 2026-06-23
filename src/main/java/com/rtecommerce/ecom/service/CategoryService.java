package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
}
