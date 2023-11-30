package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RestaurantRequest {
    private Long userId;
    private String username;
    private String title;
    private  String address;
    @Column(name = "photo")
    private String photo;
}
