package com.rso.microservice.service;


import com.rso.microservice.entity.Product;
import com.rso.microservice.entity.UserFavoriteProduct;
import com.rso.microservice.repository.UserFavoriteProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavoriteProductService {

    final UserFavoriteProductRepository userFavoriteProductRepository;

    public UserFavoriteProductService(UserFavoriteProductRepository userFavoriteProductRepository) {
        this.userFavoriteProductRepository = userFavoriteProductRepository;
    }

    public List<Product> findFavoriteProducts(Long userId) {
        return userFavoriteProductRepository.findFavoriteProductsByUserId(userId);
    }

    public UserFavoriteProduct createUserFavoriteProduct(UserFavoriteProduct userFavoriteProduct) {
        List<UserFavoriteProduct> userFavoriteProductList = userFavoriteProductRepository.findByUserAndProduct(
                userFavoriteProduct.getUser(), userFavoriteProduct.getProduct());
        if (userFavoriteProductList != null && userFavoriteProductList.size() > 0) {
            return userFavoriteProductList.get(0);
        }

        return userFavoriteProductRepository.save(userFavoriteProduct);
    }

}
