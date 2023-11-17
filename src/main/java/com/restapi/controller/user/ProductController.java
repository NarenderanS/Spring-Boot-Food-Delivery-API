package com.restapi.controller.user;

import com.restapi.model.Product;
import com.restapi.model.Role;
import com.restapi.response.ProductResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/user/product")
@RolesAllowed(Role.USER)
public class ProductController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts() {
        ProductResponse productList = productService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

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

}
