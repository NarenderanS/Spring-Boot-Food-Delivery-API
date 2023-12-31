package com.restapi.controller.user;

import com.restapi.model.Role;
import com.restapi.request.CartRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/user/cart")
@RolesAllowed(Role.USER)
public class CartController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserCart(@PathVariable Long userId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.findUserCart(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addToCart(@RequestBody CartRequest cartRequest) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.addToCart(cartRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{userId}/{cartProductId}")
    public ResponseEntity<APIResponse> updateCart(@PathVariable Long userId,@PathVariable Long cartProductId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.deleteCart(userId,cartProductId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
