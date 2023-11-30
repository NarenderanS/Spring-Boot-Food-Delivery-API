package com.restapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Getter
@Setter
public class ProductRequest {
    private Long id;
    private String title;
    private String description;
    private Double price;
//    @Transient
    private Integer count;

    private Long categoryId;
    private Long restaurantId;
    private Long vegOrNonVegId;

    private String photo;
}
