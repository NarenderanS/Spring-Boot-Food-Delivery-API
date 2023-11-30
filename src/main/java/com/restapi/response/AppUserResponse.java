package com.restapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
    private Long id;
    private String name;
    private String userName;
    private String role;
    private String gender;
    private String phoneNo;
    private Long restaurantId;
//    private List<Cart> cartList;
//    private List<OrderResponse> orderList;
//    private List<AddressResponse> addressList;

//    private AppUser appUser;


}
