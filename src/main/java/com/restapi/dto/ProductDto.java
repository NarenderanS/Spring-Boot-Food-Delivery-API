package com.restapi.dto;

import com.restapi.model.Product;
import com.restapi.request.ProductRequest;
import com.restapi.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDto {

    public ProductResponse mapToProductResponse(List<Product> productList) {
        return new ProductResponse(productList);
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
