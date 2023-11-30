package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.request.RestaurantRequest;
import com.restapi.response.RestaurantResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.RestaurantService;
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
@RequestMapping("/api/admin/restaurant")
@RolesAllowed(Role.ADMIN)
public class AdminRestaurantController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllRestaurant() {
        List<RestaurantResponse> restaurantResponse = restaurantService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> registerRestaurant(@Valid @RequestParam("title") String title,
                                                          @RequestParam("address") String address, @RequestParam("username") String username,
                                                          @RequestParam("photo") MultipartFile photo) {
        String file = storageService.storeFile(photo);
        RestaurantRequest restaurantRequest = new RestaurantRequest();
        restaurantRequest.setTitle(title);
        restaurantRequest.setPhoto(file);
        restaurantRequest.setAddress(address);
        restaurantRequest.setUsername(username);
        List<RestaurantResponse> restaurantResponse = restaurantService.createRestaurant(restaurantRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<APIResponse> deleteRestaurant(@PathVariable Long restaurantId) {
        List<RestaurantResponse> restaurantResponse = restaurantService.deleteRestaurantById(restaurantId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
