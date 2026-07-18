package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.model.Product;
import com.rtecommerce.ecom.payload.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
}
