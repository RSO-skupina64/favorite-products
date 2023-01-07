package com.rso.microservice.repository;

import com.rso.microservice.entity.Product;
import com.rso.microservice.entity.UserFavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteProductRepository extends JpaRepository<UserFavoriteProduct, Long> {

    @Query(value = "SELECT fp.product FROM UserFavoriteProduct fp LEFT JOIN fp.product p LEFT JOIN fp.user u WHERE u.id = :userId")
    List<Product> findFavoriteProductsByUserId(@Param("userId") Long userId);
}
