package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.model.Product;
import com.rtecommerce.ecom.payload.ProductDTO;
import com.rtecommerce.ecom.payload.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProduct();

    ProductResponse searchbyCategory(Long categoryId);

    ProductResponse searchProductbyKeyword(String keyword);

    ProductDTO updateProduct(Long productId, Product product);

    ProductDTO deleteProduct(Long productId);
}
