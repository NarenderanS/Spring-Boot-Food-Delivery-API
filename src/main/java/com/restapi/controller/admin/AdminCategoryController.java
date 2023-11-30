package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@RestController
@RequestMapping("/api/admin/category")
@RolesAllowed(Role.ADMIN)
public class AdminCategoryController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CategoryService categoryService;

    @PutMapping
    public ResponseEntity<APIResponse> updateCategory(@RequestBody
                                                      CategoryRequest categoryRequest) {
        List<CategoryResponse> categoryResponse = categoryService.update(categoryRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
