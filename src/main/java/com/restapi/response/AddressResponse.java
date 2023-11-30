package com.restapi.response;

import com.restapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    @Size(min = 4, message = "Password should have at least 4 characters")
    private String address;
    @Column(nullable = false, length = 100)
    @Size(min = 2, message = "City should have at least 2 characters")
    private String city;

    @Column(nullable = false, length = 10)
//    @Size(min = 6, message = "Password should have 6 digits")
    private Integer zipcode;

}
