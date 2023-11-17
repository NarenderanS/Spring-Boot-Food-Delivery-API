package com.restapi.controller.restaurant;

import com.restapi.model.Product;
import com.restapi.model.Role;
import com.restapi.request.ProductRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ProductResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant/product")
@RolesAllowed(Role.RESTAURANT)
public class RestaurantProductController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private ProductService productService;

    @GetMapping("/all/{restaurantId}")
    public ResponseEntity<APIResponse> getRestaurantProducts(@PathVariable Long restaurantId) {
        List<Product> productList = productService.findRestaurantProducts(restaurantId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<APIResponse> getCategoryProducts(@PathVariable Long id) {
        List<Product> productList = productService.findCategoryProducts(id);
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
    @PostMapping("/add")
    public ResponseEntity<APIResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productList = productService.createProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList.getProductList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productList = productService.updateProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Long id) {
        ProductResponse productResponse = productService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productResponse.getProductList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
