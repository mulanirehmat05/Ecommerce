package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.exceptions.ResourceNotFoundException;
import com.rtecommerce.ecom.model.Category;
import com.rtecommerce.ecom.model.Product;
import com.rtecommerce.ecom.payload.ProductDTO;
import com.rtecommerce.ecom.repositories.CategoryRepository;
import com.rtecommerce.ecom.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));

        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() -
                ((product.getDiscount() * 0.01)* product.getPrice());
        product.setSpecialPrice(specialPrice);

        Product savedProduct = productRepository.save(product);


        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
