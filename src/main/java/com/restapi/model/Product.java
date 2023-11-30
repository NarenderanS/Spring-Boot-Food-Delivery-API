package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    @Size(min = 4, message = "Title should have at least 4 characters")
    private String title;

    @Column(nullable = false, length = 200)
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;

    @Column(nullable = false, length = 200)
    private Double price;

    //    @Transient
    @Column(nullable = false)
    private Integer count;


    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "vegOrNonVeg_id", referencedColumnName = "id")
    private VegOrNonVeg vegOrNonVeg;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
