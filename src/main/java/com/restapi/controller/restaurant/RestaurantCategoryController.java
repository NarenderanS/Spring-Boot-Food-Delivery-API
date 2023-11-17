package com.restapi.controller.restaurant;

import com.restapi.model.Role;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/restaurant/category")
@RolesAllowed(Role.RESTAURANT)
public class RestaurantCategoryController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createCategory(@RequestBody
                                                      CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.create(categoryRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateCategory(@RequestBody
                                                      CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.update(categoryRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
