package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<Category> categories = new ArrayList<>();
    private Long NextId = 1L;

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(NextId++);
        categories.add(category);
    }
}
