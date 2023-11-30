package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.RestaurantOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderedProductsRepository orderedProductsRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderDto orderDto;
    public List<OrderResponse> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderDto.mapToOrderResponse(orderList);
    }
    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orderList = orderRepository.findUserOrder(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));
        return orderDto.mapToOrderResponse(orderList);
    }
    public List<RestaurantOrderResponse> getRestaurantOrders(Long restaurantId) {
        List<Order> orderList = orderRepository.findAll();
        return orderDto.mapToRestaurantOrderResponse(orderList,restaurantId);
    }

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }


    @Transactional
    public List<OrderResponse> placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        AppUser appUser = userService.findUserById(orderRequest.getUserId());
        Address address = addressRepository.findById(orderRequest.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", orderRequest.getAddressId()));
        OrderStatus orderStatus = orderStatusRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("orderStatusId", "OrderStatusId", 1));
        List<Cart> cartList = cartRepository.findUserCart(orderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatusId", "orderStatusId", orderRequest.getUserId()));
        order.setAppUser(appUser);
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order=orderRepository.save((order));
        for (Cart cart : cartList) {
            OrderedProduct orderedProduct=new OrderedProduct();
            orderedProduct.setOrder(order);
            orderedProduct.setTitle(cart.getProduct().getTitle());
            orderedProduct.setDescription(cart.getProduct().getDescription());
            orderedProduct.setPrice(cart.getProduct().getPrice());
            orderedProduct.setCount(cart.getCount());
            orderedProduct.setOrderTime(cart.getCreatedAt());
            orderedProduct.setProductId(cart.getProduct().getId());
            orderedProduct.setRestaurantId(cart.getProduct().getRestaurant().getId());
            orderedProductsRepository.save(orderedProduct);
            cartRepository.delete(cart);
        }

        return getUserOrders(orderRequest.getUserId());
    }

    public List<OrderResponse> updateOrderStatus(Long orderId, Long statusId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orderId", "orderId", orderId));
        OrderStatus orderStatus = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", statusId));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return getAllOrders();
    }
}
