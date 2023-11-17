package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users") // don't use User
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 4, message = "Password should have at least 4 characters")
    private String password;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "appUser")
    private List<Address> addressList;

//    @Column(nullable = false)
//    private String gender;
//
//    @Column(nullable = false, length = 15)
//    private Integer phoneNo;

    @OneToMany(mappedBy = "appUser")
    private List<Restaurant> restaurantList = new ArrayList<>();

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
