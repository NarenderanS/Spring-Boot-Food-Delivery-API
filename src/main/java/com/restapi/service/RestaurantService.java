package com.restapi.service;

import com.restapi.dto.RestaurantDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.RestaurantRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.RestaurantRequest;
import com.restapi.response.AppUserResponse;
import com.restapi.response.RestaurantResponse;
import com.restapi.response.common.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantDto restaurantDto;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<RestaurantResponse> findAll() {
        return restaurantDto.mapToRestaurantResponse(restaurantRepository.findAll());
    }

    public RestaurantResponse findById(Long restaurantId) {
        return restaurantDto.mapToRestaurantResponse(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("restaurantId", "restaurantId", restaurantId)));
    }
    public List<RestaurantResponse> createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantDto.mapToRestaurnt(restaurantRequest);
        AppUser appUser = userService.findUserByName(restaurantRequest.getUsername());
//        Role role=
        appUser.setRoles(roleRepository.findById(3).orElseThrow(()->new ResourceNotFoundException("roleId","roleId",3)));
        userRepository.save(appUser);
//        List<Product> productList = productService.findRestaurantProducts(restaurantRequest.getUserId());
        restaurant.setAppUser(appUser);
//        restaurant.setProducts(productList);
        restaurantRepository.save(restaurant);
        return restaurantDto.mapToRestaurantResponse(restaurantRepository.findAll());

    }


    public List<RestaurantResponse> deleteRestaurantById(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
        return findAll();
    }

    public List<RestaurantResponse> findByUserId(Long userId) {
        return restaurantDto.mapToRestaurantResponse(restaurantRepository.findByUserId(userId));
    }
    public File getFile(Long id) throws IOException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));
        Resource resource = storageService.loadFileAsResource(restaurant.getPhoto());
        return resource.getFile();
    }
}
