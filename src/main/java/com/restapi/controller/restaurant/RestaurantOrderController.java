package com.restapi.controller.restaurant;

import com.restapi.model.OrderStatus;
import com.restapi.model.Role;
import com.restapi.request.OrderStatusRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.RestaurantOrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant/order")
@RolesAllowed(Role.RESTAURANT)
public class RestaurantOrderController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<APIResponse> getRestaurantOrders(@PathVariable Long restaurantId) {
        List<RestaurantOrderResponse> restaurantOrderResponses = orderService.getRestaurantOrders(restaurantId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantOrderResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<APIResponse> getUsersOrder(@PathVariable Long userId) {
        List<OrderResponse> orderList = orderService.getUserOrders(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/status/all")
    public ResponseEntity<APIResponse> getAllStatus() {
        List<OrderStatus> orderList = orderService.getAllOrderStatus();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<APIResponse> updateOrderStatus(
            @RequestBody OrderStatusRequest orderStatusRequest) {
        List<OrderResponse> orderList = orderService
                .updateOrderStatus(orderStatusRequest.getOrderId(),
                        orderStatusRequest.getStatusId());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
