package com.restapi.repository;


import com.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p INNER JOIN p.restaurant r WHERE r.id=?1")
    List<Product> findByRestaurantId(Long id);

    @Query("SELECT p FROM Product p INNER JOIN p.category c WHERE c.id=?1")
    List<Product> findByCategoryId(Long id);

    @Query("SELECT p FROM Product p INNER JOIN p.vegOrNonVeg v WHERE v.id=?1")
    List<Product> findVegOrNonVegProducts(Long id);
}
