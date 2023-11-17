package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

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
    @Size(min = 6, message = "Password should have 6 digits")
    private Integer zipcode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Order order;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
