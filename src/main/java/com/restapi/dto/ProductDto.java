package com.restapi.dto;

import com.restapi.model.Product;
import com.restapi.request.ProductRequest;
import com.restapi.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDto {
    public ProductResponse mapToProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setTitle(product.getTitle());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategoryId(product.getCategory().getId());
        productResponse.setRestaurantId(product.getRestaurant().getId());
        productResponse.setCount(product.getCount());
        productResponse.setVegOrNonVegId(product.getVegOrNonVeg().getId());
        productResponse.setPhoto(product.getPhoto());
        return productResponse;
    }

    public List<ProductResponse> mapToProductResponse(List<Product> productList) {
        List<ProductResponse> productResponses=new ArrayList<>();
        for(Product product:productList) {
            productResponses.add(mapToProductResponse(product));
        }
        return productResponses;
    }

    public Product mapToProduct(ProductRequest productRequest) {
        Product product = new Product();
        if (productRequest.getId() != null) {
            product.setId(productRequest.getId());
        }
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setPhoto(productRequest.getPhoto());
        product.setCount(productRequest.getCount());
        System.out.println(productRequest.getVegOrNonVegId());
        return product;
    }
}
