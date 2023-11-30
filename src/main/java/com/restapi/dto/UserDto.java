package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.request.UserProfileRequest;
import com.restapi.response.AddressResponse;
import com.restapi.response.AppUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDto {
    @Autowired
    private AddressDto addressDto;

    public AppUser mapToUser(UserProfileRequest userProfileRequest) {
        return null;
    }

    public AppUserResponse mapToUserResponse(AppUser appUser) {
        AppUserResponse appUserResponse = new AppUserResponse();
//            appUserResponse.setAppUser(appUser);
        appUserResponse.setId(appUser.getId());
        appUserResponse.setName(appUser.getName());
        appUserResponse.setUserName(appUser.getUsername());
        appUserResponse.setRole(appUser.getRoles().getName());
        appUserResponse.setGender(appUser.getGender());
        appUserResponse.setPhoneNo(appUser.getPhoneNo());
        if(appUser.getRestaurantList().isEmpty()){
            appUserResponse.setRestaurantId(0L);
        }
        else {
            appUserResponse.setRestaurantId(appUser.getRestaurantList().get(0).getId());
        }
//            appUserResponse.setCartList(appUserResponse.getCartList());
//            appUserResponse.setOrderList(appUserResponse.getOrderList());
//            appUserResponse.setAddressList(appUserResponse.getAddressList());
        return appUserResponse;
    }

    public List<AppUserResponse> mapToUserResponse(List<AppUser> appUsers) {
        List<AppUserResponse> appUserResponses = new ArrayList<>();
        for (AppUser appUser : appUsers) {

            appUserResponses.add(mapToUserResponse(appUser));
        }
        return appUserResponses;
    }
}
