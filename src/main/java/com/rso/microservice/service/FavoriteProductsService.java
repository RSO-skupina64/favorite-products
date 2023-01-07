package com.rso.microservice.service;


import com.rso.microservice.entity.Product;
import com.rso.microservice.repository.UserFavoriteProductRepository;
import com.rso.microservice.vao.FavoriteProductsListVAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteProductsService {

    final UserFavoriteProductRepository userFavoriteProductRepository;

    public FavoriteProductsService(UserFavoriteProductRepository userFavoriteProductRepository) {
        this.userFavoriteProductRepository = userFavoriteProductRepository;
    }

    public FavoriteProductsListVAO getFavoriteProductsByUserId(Long userId) {
        List<Product> favoriteProducts = userFavoriteProductRepository.findFavoriteProductsByUserId(userId);

        return new FavoriteProductsListVAO(favoriteProducts.size(), favoriteProducts);
    }

}
