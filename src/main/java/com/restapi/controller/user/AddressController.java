package com.restapi.controller.user;

import com.restapi.model.Role;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/user/address")
@RolesAllowed(Role.USER)
public class AddressController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserAddress(@PathVariable Long userId) {
        List<AddressResponse> addressResponse = addressService.findUserAddressById(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public  ResponseEntity<APIResponse> createAddress(@RequestBody AddressRequest addressRequest){
        List<AddressResponse> addressResponse=addressService.create(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<APIResponse> updateAddress(@RequestBody
                                                     AddressRequest addressRequest) {
        List<AddressResponse> addressResponse = addressService.update(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteAddress(@PathVariable Integer id) {
        List<AddressResponse> addressResponse = addressService
                .deleteById(Long.valueOf(id));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
