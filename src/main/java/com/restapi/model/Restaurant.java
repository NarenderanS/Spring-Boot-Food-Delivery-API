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
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Column(name = "title", unique = true, nullable = false, length = 100)
//    @NotEmpty
//    @Size(min = 2, message = "Username should have at least 2 characters")
    @Column(nullable = false, length = 200)
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    @Column(name = "photo")
    private String photo;

    @Column(nullable = false)
    private String address;
    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProducts;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
