package com.restapi.controller.user;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.request.AddressRequest;
import com.restapi.request.CategoryRequest;
import com.restapi.request.UserProfileRequest;
import com.restapi.response.AddressResponse;
import com.restapi.response.AppUserResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import com.restapi.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RolesAllowed(Role.USER)
public class UserController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserDetails(@PathVariable Long userId) {
        AppUserResponse appUserResponse = userService.findUserDetailsById(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appUserResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateUserDetails( @RequestBody UserProfileRequest userProfileRequest) {
        System.out.println(userProfileRequest.getName());
        AppUser appUser = userService.updateUserDetails(userProfileRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userProfileRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
