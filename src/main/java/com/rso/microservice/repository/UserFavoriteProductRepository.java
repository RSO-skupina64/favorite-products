package com.rso.microservice.repository;

import com.rso.microservice.entity.Product;
import com.rso.microservice.entity.User;
import com.rso.microservice.entity.UserFavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserFavoriteProductRepository extends JpaRepository<UserFavoriteProduct, Long> {

    List<UserFavoriteProduct> findByUserAndProduct(User user, Product product);

    @Modifying
    @Query("DELETE FROM UserFavoriteProduct fp WHERE fp.user.id = :userId AND fp.product.id = :productId")
    void deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
