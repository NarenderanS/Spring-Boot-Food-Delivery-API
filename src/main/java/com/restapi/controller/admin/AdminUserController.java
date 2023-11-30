package com.restapi.controller.admin;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.response.AppUserResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.StorageService;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@RolesAllowed(Role.ADMIN)
public class AdminUserController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public ResponseEntity<APIResponse> getAllUsers() {
        List<AppUserResponse> appUsers = userService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appUsers);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<APIResponse> getUserById(@PathVariable long id) {
        AppUserResponse appUserResponse = userService.findUserDetailsById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appUserResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @Autowired
//    private StorageService storageService;
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<APIResponse> dummy(@RequestParam("photo") MultipartFile photo) {
//        String file = storageService.storeFile(photo);
//        apiResponse.setStatus(HttpStatus.OK.value());
////        apiResponse.setData(appUserResponse);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }

}
