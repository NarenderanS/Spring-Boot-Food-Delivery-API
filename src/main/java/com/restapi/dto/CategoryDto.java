package com.restapi.dto;

import com.restapi.model.Category;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDto {
    public CategoryResponse mapToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setTitle(category.getTitle());
        categoryResponse.setPhoto(category.getPhoto());
        return categoryResponse;
    }

    public List<CategoryResponse> mapToCategoryResponse(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(mapToCategoryResponse(category));
        }
        return categoryResponses;
    }

    public Category mapToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        if (categoryRequest.getId() != null) {
            category.setId(categoryRequest.getId());
        }
        category.setTitle(categoryRequest.getTitle());
        category.setPhoto(categoryRequest.getPhoto());
        return category;
    }
}
