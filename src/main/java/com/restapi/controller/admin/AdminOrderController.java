package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.response.OrderResponse;
import com.restapi.response.RestaurantOrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
@RolesAllowed(Role.ADMIN)
public class AdminOrderController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private OrderService orderService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllOrders() {
        List<OrderResponse> restaurantOrderResponses = orderService.getAllOrders();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurantOrderResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
