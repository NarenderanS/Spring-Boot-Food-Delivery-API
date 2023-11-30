package com.restapi.controller.user;

import com.restapi.model.Role;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import com.restapi.service.OrderService;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/user/order")
@RolesAllowed(Role.USER)
public class OrderController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserOrders(@PathVariable Long userId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderService.getUserOrders(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        List<OrderResponse> orderResponses=orderService.placeOrder(orderRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
