package com.restapi.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {
    private Long userId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Integer count;
}
