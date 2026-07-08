package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.model.Category;
import com.rtecommerce.ecom.payload.CategoryDTO;
import com.rtecommerce.ecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
