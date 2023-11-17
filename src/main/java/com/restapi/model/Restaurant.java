package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
