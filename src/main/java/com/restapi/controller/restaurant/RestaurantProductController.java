package com.restapi.controller.restaurant;

import com.restapi.model.Product;
import com.restapi.model.Role;
import com.restapi.request.ProductRequest;
import com.restapi.response.AppUserResponse;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ProductResponse;
import com.restapi.response.RestaurantResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProductService;
import com.restapi.service.RestaurantService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<APIResponse> getRestaurantDetails(@PathVariable Long userId) {
        List<RestaurantResponse> restaurantResponse = restaurantService.findByUserId(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<APIResponse> getRestaurantProducts(@PathVariable Long restaurantId) {
        List<ProductResponse> productList = productService.findRestaurantProducts(restaurantId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<APIResponse> getCategoryProducts(@PathVariable Long id) {
        List<ProductResponse> productList = productService.findCategoryProducts(id);
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

    //    @PostMapping("/add")
//    public ResponseEntity<APIResponse> createProduct(@RequestBody ProductRequest productRequest) {
//        List<ProductResponse> productList = productService.createProduct(productRequest);
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(productList);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createProduct(@RequestParam("photo") MultipartFile photo,
                                                     @RequestParam("title") String title,
                                                     @RequestParam("description") String description,
                                                     @RequestParam("price") Double price,
                                                     @RequestParam("count") Integer count,
                                                     @RequestParam("categoryId") Long categoryId,
                                                     @RequestParam("restaurantId") Long restaurantId,
                                                     @RequestParam("vegOrNonVegId") Long vegOrNonVegId
    ) {
        String file = storageService.storeFile(photo);
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPhoto(file);
        productRequest.setTitle(title);
        productRequest.setDescription(description);
        productRequest.setPrice(price);
        productRequest.setCount(count);
        productRequest.setVegOrNonVegId(vegOrNonVegId);
        productRequest.setRestaurantId(restaurantId);
        productRequest.setCategoryId(categoryId);
        List<ProductResponse> productList = productService.createProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductRequest productRequest) {
        List<ProductResponse> productList = productService.updateProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Long id) {
        List<ProductResponse> productResponse = productService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
