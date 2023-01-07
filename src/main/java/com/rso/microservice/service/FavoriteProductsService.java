package com.rso.microservice.service;


import com.rso.microservice.entity.Product;
import com.rso.microservice.entity.User;
import com.rso.microservice.entity.UserFavoriteProduct;
import com.rso.microservice.vao.FavoriteProductsListVAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteProductsService {

    final UserFavoriteProductService userFavoriteProductService;
    final ProductService productService;
    final UserService userService;

    public FavoriteProductsService(UserFavoriteProductService userFavoriteProductService, ProductService productService,
                                   UserService userService) {
        this.userFavoriteProductService = userFavoriteProductService;
        this.productService = productService;
        this.userService = userService;
    }

    public FavoriteProductsListVAO getFavoriteProductsByUserId(Long userId) {
        List<Product> favoriteProducts = productService.findFavoriteProducts(userId);

        return new FavoriteProductsListVAO(favoriteProducts.size(), favoriteProducts);
    }

    public UserFavoriteProduct createUserFavoriteProduct(Long userId, Long productId) {
        UserFavoriteProduct userFavoriteProduct = new UserFavoriteProduct();
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            userFavoriteProduct.setUser(userOptional.get());
        } else {
            return null;
        }
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            userFavoriteProduct.setProduct(productOptional.get());
        } else {
            return null;
        }

        return userFavoriteProductService.createUserFavoriteProduct(userFavoriteProduct);
    }

    public void removeUserFavoriteProduct(Long userId, Long productId) {
        userFavoriteProductService.removeUserFavoriteProduct(userId, productId);
    }

}
