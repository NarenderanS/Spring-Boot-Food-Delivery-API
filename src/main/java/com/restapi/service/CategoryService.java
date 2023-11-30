package com.restapi.service;

import com.restapi.dto.CategoryDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Product;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private  StorageService storageService;
    @Autowired
    private CategoryDto categoryDto;

    public List<CategoryResponse> findAll() {
        return categoryDto.mapToCategoryResponse(categoryRepository.findAll());
    }
    public CategoryResponse findById(Long id) {
        return  categoryDto.mapToCategoryResponse(categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("categoryId","categoryId",id)));
    }
    public List<CategoryResponse> create(CategoryRequest categoryRequest) {
        Category category = categoryDto.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    public List<CategoryResponse> update(CategoryRequest categoryRequest) {
        Category category = categoryDto.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    public List<CategoryResponse> deleteById(Long id) {
        categoryRepository.deleteById(id);
        return findAll();
    }
    public File getFile(Long id) throws IOException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));
        Resource resource = storageService.loadFileAsResource(category.getPhoto());
        return resource.getFile();
    }

}
