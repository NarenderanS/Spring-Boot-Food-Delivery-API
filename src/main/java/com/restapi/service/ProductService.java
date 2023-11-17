package com.restapi.service;

import com.restapi.dto.ProductDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.ProductRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductDto productDto;
    @Autowired
    private UserService userService;
    @Autowired
    private VegOrNonVegRepository vegOrNonVegRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public ProductResponse findAll() {
        List<Product> productList = productRepository.findAll();
        return productDto.mapToProductResponse(productList);

    }

    public List<Product> findRestaurantProducts(Long restaurantId) {
        return productRepository.findByRestaurantId(restaurantId);
    }
    public List<Product> findCategoryProducts(Long id) {
        return productRepository.findByCategoryId(id);
    }
    public List<Product> findVegOrNonVegProducts(Long id) {
        return productRepository.findVegOrNonVegProducts(id);
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productDto.mapToProduct(productRequest);
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category",
                        "CategoryId", productRequest.getCategoryId()));
        product.setCategory(category);
        VegOrNonVeg vegOrNonVeg = vegOrNonVegRepository.findById(productRequest.getVegOrNonVegId())
                .orElseThrow(() -> new ResourceNotFoundException("vegOrNonVeg", "vegOrNonVegId", productRequest.getVegOrNonVegId()));
        product.setVegOrNonVeg(vegOrNonVeg);
        Restaurant restaurant = restaurantRepository.findById(productRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "RestaurantId", productRequest.getRestaurantId()));
        product.setRestaurant(restaurant);
        productRepository.save(product);
        return findAll();
    }

    public ProductResponse updateProduct(ProductRequest productRequest) {
        Product product = productDto.mapToProduct(productRequest);
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category",
                        "CategoryId", productRequest.getCategoryId()));
        product.setCategory(category);
        VegOrNonVeg vegOrNonVeg = vegOrNonVegRepository.findById(productRequest.getVegOrNonVegId())
                .orElseThrow(() -> new ResourceNotFoundException("vegOrNonVeg", "vegOrNonVegId", productRequest.getVegOrNonVegId()));
        product.setVegOrNonVeg(vegOrNonVeg);
        Restaurant restaurant = restaurantRepository.findById(productRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "RestaurantId", productRequest.getRestaurantId()));
        product.setRestaurant(restaurant);
        productRepository.save(product);
        return findAll();
    }

    public ProductResponse deleteById(Long id) {
        productRepository.deleteById(id);
        return findAll();
    }
}
