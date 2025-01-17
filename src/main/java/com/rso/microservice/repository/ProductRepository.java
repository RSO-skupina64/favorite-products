package com.rso.microservice.repository;

import com.rso.microservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT fp.product FROM UserFavoriteProduct fp WHERE fp.user.id = :userId")
    List<Product> findFavoriteProductsByUserId(@Param("userId") Long userId);
}
