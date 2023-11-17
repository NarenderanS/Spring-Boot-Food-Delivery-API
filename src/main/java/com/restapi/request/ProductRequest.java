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
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false, length = 200)
    private Double price;
//    @Transient
    private Integer count;

    @Column(nullable = false)
    private Long categoryId;
    @Column(nullable = false)
    private Long restaurantId;
    @Column(nullable = false)
    private Long vegOrNonVegId;
    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;
}
