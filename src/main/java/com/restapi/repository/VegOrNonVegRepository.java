package com.restapi.repository;

import com.restapi.model.VegOrNonVeg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegOrNonVegRepository extends JpaRepository<VegOrNonVeg, Long> {

    VegOrNonVeg findByName(String title);
}
