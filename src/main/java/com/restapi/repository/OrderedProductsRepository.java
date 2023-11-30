package com.restapi.repository;

import com.restapi.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProduct, Long> {

}
