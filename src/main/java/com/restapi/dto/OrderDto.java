package com.restapi.dto;

import com.restapi.model.Order;
import com.restapi.model.OrderedProduct;
import com.restapi.response.OrderResponse;
import com.restapi.response.RestaurantOrderResponse;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class OrderDto {

    public List<OrderResponse> mapToOrderResponse(List<Order> orderList) {
        List<OrderResponse> orderResponseList = new ArrayList<>();

        for (Order order : orderList) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setUserId(order.getAppUser().getId());
            orderResponse.setName(order.getAppUser().getName());
            orderResponse.setUsername(order.getAppUser().getUsername());
            orderResponse.setOrderStatus(order.getOrderStatus().getStatus());
            orderResponse.setAddress(order.getAddress());
            orderResponse.setOrderedProducts(order.getOrderedProducts());
//            Formatting the date
            LocalDateTime time=order.getOrderTime();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy E-hh:mm:ss");
//            System.out.println(dateTimeFormatter.format(time));
            orderResponse.setOrderTime(dateTimeFormatter.format(time));
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }

    public List<RestaurantOrderResponse> mapToRestaurantOrderResponse(List<Order> orderList, Long restaurantId) {
        List<RestaurantOrderResponse> restaurantOrderResponses=new ArrayList<>();
        for(Order order:orderList){
            RestaurantOrderResponse restaurantOrderResponse=new RestaurantOrderResponse();
            restaurantOrderResponse.setId(order.getId());
            restaurantOrderResponse.setName(order.getAppUser().getName());
            restaurantOrderResponse.setUsername(order.getAppUser().getUsername());
            restaurantOrderResponse.setAddress(order.getAddress());
            restaurantOrderResponse.setOrderStatus(order.getOrderStatus().getStatus());
            restaurantOrderResponse.setRestaurantId(restaurantId);
//                    Formatting the date
            LocalDateTime time=order.getOrderTime();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy E-hh:mm:ss");
            restaurantOrderResponse.setOrderTime(dateTimeFormatter.format(time));

            List<OrderedProduct> orderedProducts=new ArrayList<>();
            for(OrderedProduct orderedProduct:order.getOrderedProducts()){
                if(Objects.equals(orderedProduct.getRestaurantId(), restaurantId)){
                    orderedProducts.add(orderedProduct);
                }
            }
            if(!orderedProducts.isEmpty()){
                restaurantOrderResponse.setOrderedProducts(orderedProducts);
                restaurantOrderResponses.add(restaurantOrderResponse);
            }

        }
        return restaurantOrderResponses;
    }
}
