package com.restapi.response;

import com.restapi.model.Product;
import com.restapi.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {

    private Long id;
    private String title;
    private String address;
    private Long restaurantId;
    @Column(name = "photo")
    private String photo;
    private List<ProductResponse> productList;

}
