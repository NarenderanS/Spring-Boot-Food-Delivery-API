package com.restapi.service;

import com.restapi.dto.AuthDto;
import com.restapi.dto.AuthDto;
import com.restapi.dto.UserDto;
import com.restapi.exception.common.AppException;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.request.UserProfileRequest;
import com.restapi.response.AppUserResponse;
import com.restapi.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;
    @Autowired
    private UserDto userDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthResponse register(RegisterRequest registerRequest) {
        AppUser appUser = authDto.mapToAppUser(registerRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(Role.USER));
        appUser = userRepository.save(appUser);
        return authDto.mapToAuthResponse(appUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }
        return authDto.mapToAuthResponse(appUser);
    }

    public List<AppUserResponse> findAll() {
        return userDto.mapToUserResponse(userRepository.findAll());
    }

    public AppUserResponse findUserDetailsById(Long userId) {
        return userDto.mapToUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId)));
    }
    public AppUser findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
    }
    public AppUser findUserByName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found with username: " + username);
        });
    }
//Error
    public AppUser updateUserDetails(UserProfileRequest userProfileRequest) {
        AppUser appUser = userRepository.findById(userProfileRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userProfileRequest.getUserId()));
        appUser.setName(userProfileRequest.getName());
        appUser.setPassword(bCryptPasswordEncoder.encode(userProfileRequest.getPassword()));
        userRepository.save(appUser);
        return appUser;
    }


}
