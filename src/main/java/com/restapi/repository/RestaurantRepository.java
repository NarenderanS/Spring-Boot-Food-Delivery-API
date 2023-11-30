package com.restapi.repository;

import com.restapi.model.Product;
import com.restapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    @Query("SELECT r FROM Restaurant r INNER JOIN r.appUser u WHERE u.id=?1")
    List<Restaurant> findByUserId(Long id);
}
