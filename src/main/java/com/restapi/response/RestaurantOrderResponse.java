package com.restapi.response;

import com.restapi.model.Address;
import com.restapi.model.OrderedProduct;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOrderResponse {
    private Long id;
    private String name;
    private String username;
    private Address address;
    private String orderStatus;
    private String orderTime;
    private Long restaurantId;
    private List<OrderedProduct> orderedProducts;

}
