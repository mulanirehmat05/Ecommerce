package com.rtecommerce.ecom.controller;

import com.rtecommerce.ecom.config.AppConstants;
import com.rtecommerce.ecom.model.Category;
import com.rtecommerce.ecom.payload.CategoryDTO;
import com.rtecommerce.ecom.payload.CategoryResponse;
import com.rtecommerce.ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /*@GetMapping("/api/public/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "test", required = false) String message){
        //public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "Hello World!") String message){
        return new ResponseEntity<>("Echoed message: " + message, HttpStatus.OK);
    }*/

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getCategories(
            @RequestParam(name = "PageNumber", defaultValue = AppConstants.PAGENUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "PageSize", defaultValue = AppConstants.PAGESIZE, required = false) Integer pageSize,
            @RequestParam(name = "SortBy", defaultValue = AppConstants.SORTBY, required = false) String sortBy,
            @RequestParam(name = "SortOrder", defaultValue = AppConstants.SORTDIR, required = false) String sortOrder)


    {
        CategoryResponse categoryResponse = categoryService.getCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategories(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = categoryService.createCategory(categoryDTO );
        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
            CategoryDTO status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable Long categoryId){
            CategoryDTO existingCategory = categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>(existingCategory, HttpStatus.OK);
    }


}
