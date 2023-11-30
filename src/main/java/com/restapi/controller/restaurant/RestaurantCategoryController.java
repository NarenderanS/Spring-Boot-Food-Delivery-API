package com.restapi.controller.restaurant;

import com.restapi.model.Role;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant/category")
@RolesAllowed(Role.RESTAURANT)
public class RestaurantCategoryController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StorageService storageService;

    //    no need
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategories() {
        List<CategoryResponse> categoryResponse = categoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createCategory(@RequestParam("photo") MultipartFile photo, @RequestParam("title") String title) {
        System.out.println(photo);
        System.out.println(title);
        String file = storageService.storeFile(photo);
        CategoryRequest categoryRequest = new CategoryRequest();
        System.out.println(file);
        categoryRequest.setPhoto(file);
        categoryRequest.setTitle(title);
        List<CategoryResponse> categoryResponse = categoryService.create(categoryRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Long id) {
        List<CategoryResponse> categoryResponse = categoryService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
