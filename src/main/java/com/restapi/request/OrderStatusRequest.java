package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusRequest {
    private Long orderId;
    private Long statusId;
}
