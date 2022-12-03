package com.rso.microservice.service;


import com.rso.microservice.entity.Product;
import com.rso.microservice.repository.FavoriteProductsRepository;
import com.rso.microservice.vao.FavoriteProductListVAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteProductsService {

    final FavoriteProductsRepository favoriteProductsRepository;

    public FavoriteProductsService(FavoriteProductsRepository favoriteProductsRepository) {
        this.favoriteProductsRepository = favoriteProductsRepository;
    }

    public FavoriteProductListVAO getFavoriteProductsByUserId(Long userId) {
        List<Product> favoriteProducts = favoriteProductsRepository.findFavoriteProductsByUserId(userId);

        return new FavoriteProductListVAO(favoriteProducts.size(), favoriteProducts);
    }

}
