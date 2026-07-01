package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.exceptions.APIExceptions;
import com.rtecommerce.ecom.exceptions.ResourceNotFoundException;
import com.rtecommerce.ecom.model.Category;
import com.rtecommerce.ecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<Category> categories = new ArrayList<>();
    //private Long NextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty())
            throw new APIExceptions("No category created till now.");
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null)
            throw new APIExceptions("Category with the name " + category.getCategoryName() + " already exists !!!");
        //category.setCategoryId(NextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));



        categoryRepository.delete(category);
        return "Category with CategoryId: " +categoryId + " deleted!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingcategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));



        category.setCategoryId(categoryId);
        existingcategory = categoryRepository.save(category);
        return existingcategory;
    }


}
