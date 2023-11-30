package com.restapi.response;

import com.restapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private long id;
    private String title;
    private String description;
    private double price;
    private long categoryId;
    private long restaurantId;
    private int count;
    private long vegOrNonVegId;
    @Column(name = "photo")
    private String photo;
}
