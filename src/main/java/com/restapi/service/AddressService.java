package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressDto addressDto;

    public List<AddressResponse> findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressDto.mapToAddressResponse(addressList);
    }
    public List<AddressResponse> findUserAddressById(Long userId) {
        return addressDto.mapToAddressResponse(addressRepository.findUserAddressById(userId));
    }
    @Transactional
    public List<AddressResponse> create(AddressRequest addressRequest) {
        Address address = addressDto.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);
        return findUserAddressById(appUser.getId());
    }
    public List<AddressResponse> update(AddressRequest addressRequest) {
        Address address = addressDto.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);
        return findUserAddressById(appUser.getId());
    }

    public List<AddressResponse> deleteById(Long id) {
        Long userId=addressRepository.findById(id).get().getAppUser().getId();
        addressRepository.deleteById(id);
        return findUserAddressById(userId);
    }
}
