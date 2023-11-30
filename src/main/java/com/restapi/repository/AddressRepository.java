package com.restapi.repository;


import com.restapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a INNER JOIN a.appUser u WHERE u.id=?1")
    List<Address> findUserAddressById(Long userId);
}
