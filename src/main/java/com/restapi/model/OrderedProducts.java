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
public class OrderedProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(nullable = false, length = 200)
    @Size(min = 4, message = "Title should have at least 4 characters")
    private String title;

    @Column(nullable = false, length = 200)
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;

    @Column(nullable = false, length = 200)
    private Double price;

    private Integer count = 1;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime orderTime;
}
