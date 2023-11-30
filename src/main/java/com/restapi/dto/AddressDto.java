package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressDto {

    public List<AddressResponse> mapToAddressResponse(List<Address> addressList) {
        List<AddressResponse> addressResponses=new ArrayList<>();
        for(Address address:addressList){
            AddressResponse addressResponse=new AddressResponse();
            addressResponse.setId(address.getId());
            addressResponse.setAddress(address.getAddress());
            addressResponse.setCity(address.getCity());
            addressResponse.setZipcode(address.getZipcode());

            addressResponses.add(addressResponse);
        }
        return addressResponses;
    }
    public Address mapToAddress(AddressRequest addressRequest) {
        Address address = new Address();
        if (addressRequest.getId() != null) {
            address.setId(addressRequest.getId());
        }
        address.setAddress(addressRequest.getAddress());
        address.setCity(addressRequest.getCity());
        address.setZipcode(addressRequest.getZipcode());
        return address;
    }
}
