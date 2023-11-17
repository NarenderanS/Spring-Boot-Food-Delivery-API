package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class AddressRequest {
    private Long id;
    private Long userId;
    @Column(nullable = false, length = 200)
    private String address;
    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false, length = 10)
    private Integer zipcode;
}
