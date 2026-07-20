package com.rtecommerce.ecom.service;

import com.rtecommerce.ecom.exceptions.ResourceNotFoundException;
import com.rtecommerce.ecom.model.Category;
import com.rtecommerce.ecom.model.Product;
import com.rtecommerce.ecom.payload.CategoryDTO;
import com.rtecommerce.ecom.payload.ProductDTO;
import com.rtecommerce.ecom.payload.ProductResponse;
import com.rtecommerce.ecom.repositories.CategoryRepository;
import com.rtecommerce.ecom.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ProductResponse getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchbyCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category","categoryId",categoryId));

        List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductbyKeyword(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, Product product) {

        //Get Existing Product From DB
        Product existingproduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product","productId",productId));


        existingproduct.setProductName(product.getProductName());
        existingproduct.setDescription(product.getDescription());
        existingproduct.setQuantity(product.getQuantity());
        existingproduct.setPrice(product.getPrice());
        existingproduct.setDiscount(product.getDiscount());
        double specialPrice = existingproduct.getPrice() -
                ((existingproduct.getDiscount() * 0.01)* existingproduct.getPrice());
        existingproduct.setSpecialPrice(specialPrice);
        existingproduct.setImage("default.png");

        Product updatedProduct = productRepository.save(existingproduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product","productId",productId));

            productRepository.delete(product);
            return modelMapper.map(product, ProductDTO.class);
        }
}
