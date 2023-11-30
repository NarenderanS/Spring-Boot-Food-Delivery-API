package com.restapi.controller;

import com.restapi.model.Product;
import com.restapi.model.Role;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ProductResponse;
import com.restapi.response.RestaurantResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import com.restapi.service.ProductService;
import com.restapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts() {
        List<ProductResponse> productResponses = productService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getProduct(@PathVariable Long id) {
        ProductResponse productResponses = productService.findById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<APIResponse> getCategoryProducts(@PathVariable Long id) {
        List<ProductResponse> productList = productService.findCategoryProducts(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<APIResponse> getRestaurantProducts(@PathVariable Long id) {
        List<ProductResponse> productList = productService.findRestaurantProducts(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/vegOrNonVeg/{id}")
    public ResponseEntity<APIResponse> getVegOrNonVegProducts(@PathVariable Long id) {
        List<Product> productList = productService.findVegOrNonVegProducts(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Long id){
        List<ProductResponse> productList = productService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
