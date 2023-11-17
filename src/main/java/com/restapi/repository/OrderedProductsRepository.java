package com.restapi.repository;

import com.restapi.model.OrderedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, Long> {

}
