package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileRequest {

    private Long userId;

    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String name;

    private String password;
}
